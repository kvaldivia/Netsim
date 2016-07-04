package org.netsim.networking.device;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IProtocol;

// TODO: The access point doesn't need to know about the content of the
// IFrame to send it out. The nature of the radio waves makes the WirelessLink
// object implement a broadcast mechanism.
public class AccessPoint implements IDevice<IFrame> {
  private LinkedHashMap<String,IHardwareInterface<? extends IFrame>> interfaces;
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

  @Override
  public void addEthernetInterface(EthernetInterface eth) {
    String id = "eth" + ethCounter;
    this.interfaces.put(id, eth);
    eth.setDevice(this);
    ethCounter++;
  }

  @Override
  public void addWlanInterface(WlanInterface wlan) {
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

    for (IHardwareInterface<? extends IFrame> hwInterface
         : interfaces.values()) {
      hwInterface.send(msg.getPayload(), src, dest, prot);
    } 
  }

  public void run() {
    for (IHardwareInterface<? extends IFrame> iface: interfaces.values()) {
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
  public ArrayList<IHardwareInterface<? extends IFrame>> listInterfaces() {
    ArrayList<IHardwareInterface<? extends IFrame>> result;
    result = new ArrayList<>(interfaces.values());
    return result;
  }

  @Override
  public ArrayList<IHardwareInterface<? extends IFrame>> listConnected() {
    ArrayList<IHardwareInterface<? extends IFrame>> connected;
    connected = new ArrayList<>();
    for (IHardwareInterface<? extends IFrame> iface: interfaces.values()) {
      connected.addAll(iface.getConnectedHosts());
    }
    return connected;
  }

  @Override
  public ArrayList<IHardwareInterface<? extends IFrame>> 
  listWirelessInterfaces() {
    ArrayList<IHardwareInterface<? extends IFrame>> wlanList 
      = new ArrayList<>();

    for (Map.Entry<String,IHardwareInterface<? extends IFrame>> wlan
         : interfaces.entrySet()) {

      if (wlan.getKey().contains("wlan")) {
        wlanList.add(wlan.getValue());
      }
    }
    return wlanList;
  }

}
