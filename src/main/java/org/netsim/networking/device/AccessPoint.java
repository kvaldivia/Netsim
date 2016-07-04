package org.netsim.networking.device;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IProtocol;

// TODO: The access point doesn't need to know about the content of the
// IFrame to send it out. The nature of the radio waves makes the WirelessLink
// object implement a broadcast mechanism.
public class AccessPoint extends ADevice<IFrame> {
  private LinkedHashMap<String,AHardwareInterface<? extends IFrame>> interfaces;
  private LinkedList<IFrame> outgoingQueue;

  private int wlanCounter;
  private int ethCounter;
  private boolean deviceIsOn;

  private static final int INT_NUM_THREADS = 10;

  private Thread thread;
  private ExecutorService executor;

  @Inject
  public AccessPoint() {
    wlanCounter = 0;
    ethCounter = 0;
    deviceIsOn = true;
    thread = null;
    wlanCounter = 0;
    ethCounter = 0;
    interfaces = new LinkedHashMap<>(2);
    outgoingQueue = new LinkedList<>();
    executor  = Executors.newFixedThreadPool(INT_NUM_THREADS);
  }

  public void addEthernetInterface(AHardwareInterface<?extends IFrame> eth) {
    String id = "eth" + ethCounter;
    this.interfaces.put(id, eth);
    eth.setDevice(this);
    ethCounter++;
  }

  public void addWlanInterface(AHardwareInterface<?extends IFrame> wlan) {
    String id = "wlan" + wlanCounter;
    this.interfaces.put(id, wlan);
    wlan.setDevice(this);
    wlanCounter++;
  }

  private IFrame dequeueOutgoing() {
    synchronized (outgoingQueue) {
      try {
        return outgoingQueue.getLast();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      } 
      return null;
    }
  }

  public void process() {
    IFrame msg = dequeueOutgoing();
    if (msg == null) {
      return;
    }

    String src = msg.getSourceAddress();
    String dest = msg.getDestinationAddress();
    String prot = msg.getProtocol();

    for (AHardwareInterface<? extends IFrame> hwInterface
         : interfaces.values()) {
      hwInterface.send(msg.getPayload(), src, dest, prot);
    } 
  }

  public void run() {
    for (AHardwareInterface<? extends IFrame> iface: interfaces.values()) {
      executor.execute(iface);
    }
    while (deviceIsOn) {
      process();
    }
  }

  public void setThread(Thread t) {
    if (thread == null)
      thread = t;
  }

  public <T1 extends IFrame> 
    void receiveMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr) {

    synchronized(outgoingQueue) {
      outgoingQueue.addLast(msg);
    }
  }

  public <T1 extends IFrame> 
    void sendMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr) {
    return;
  }

  @Override
  public ArrayList<AHardwareInterface<? extends IFrame>> listInterfaces() {
    ArrayList<AHardwareInterface<? extends IFrame>> result;
    result = new ArrayList<>(interfaces.values());
    return result;
  }

  @Override
  public ArrayList<AHardwareInterface<? extends IFrame>> listConnected() {
    ArrayList<AHardwareInterface<? extends IFrame>> connected;
    connected = new ArrayList<>();
    for (AHardwareInterface<? extends IFrame> iface: interfaces.values()) {
      connected.addAll(iface.getConnectedHosts());
    }
    return connected;
  }

  @Override
  public ArrayList<AHardwareInterface<? extends IFrame>> 
  listWirelessInterfaces() {
    ArrayList<AHardwareInterface<? extends IFrame>> wlanList 
      = new ArrayList<>();

    for (Map.Entry<String,AHardwareInterface<? extends IFrame>> wlan
         : interfaces.entrySet()) {

      if (wlan.getKey().contains("wlan")) {
        wlanList.add(wlan.getValue());
      }
    }
    return wlanList;
  }

}
