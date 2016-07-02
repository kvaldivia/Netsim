package org.netsim.device;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.netsim.networking.AHardwareInterface;
import org.netsim.networking.IDataUnit;
import org.netsim.networking.IFrame;
import org.netsim.networking.IPacket;
import org.netsim.networking.IProtocol;

import javax.inject.Inject;

// TODO: The access point doesn't need to know about the content of the
// IFrame to send it out. The nature of the radio waves makes the WirelessLink
// object implement a broadcast mechanism.
public class AccessPoint extends ADevice<IFrame> {
  private LinkedHashMap<String,AHardwareInterface<?extends IPacket,?extends IFrame>> interfaces;
  private LinkedList<IFrame> outgoingQueue;

  private int wlanCounter;
  private int ethCounter;
  private boolean deviceIsOn;

  private Thread thread;

  public AccessPoint() {
    wlanCounter = 0;
    ethCounter = 0;
    deviceIsOn = true;
    thread = null;
    wlanCounter = 0;
    ethCounter = 0;
    interfaces = new LinkedHashMap<>(2);
    outgoingQueue = new LinkedList<>();
  }

  @Inject
  public void addEthernetInterface(AHardwareInterface<?extends IPacket,?extends IFrame> eth) {
    String id = "eth" + ethCounter;
    this.interfaces.put(id, eth);
    eth.setDevice(this);
    ethCounter++;
  }

  @Inject
  public void addWlanInterface(AHardwareInterface<?extends IPacket,?extends IFrame> wlan) {
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

    for (AHardwareInterface<? extends IPacket, ? extends IFrame> hwInterface
         : interfaces.values()) {
      hwInterface.send(msg.getPayload(), src, dest, prot);
    } 
  }

  public void run() {
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
  public ArrayList<AHardwareInterface<? extends IPacket, ? extends IFrame>> listInterfaces() {
    ArrayList<AHardwareInterface<? extends IPacket, ? extends IFrame>> result;
    result = new ArrayList<>(interfaces.values());
    return result;
  }

  @Override
  public ArrayList<AHardwareInterface<? extends IPacket, ? extends IFrame>> listConnected() {
    // TODO Auto-generated method stub
    return null;
  }

}
