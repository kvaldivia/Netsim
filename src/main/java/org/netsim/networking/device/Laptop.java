package org.netsim.networking.device;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IMessage;
import org.netsim.networking.protocol.IProtocol;

public class Laptop implements IConsumerDevice {
  private LinkedHashMap<String,IHardwareInterface<? extends IFrame>> interfaces;
  private LinkedHashMap<String,IProtocol<? extends IDataUnit, ? extends IDataUnit>> protocols;
  private LinkedList<DefaultKeyValue<String,IMessage>> outgoingQueue;
  private LinkedList<DefaultKeyValue<String,IMessage>> incomingQueue;

  private int wlanCounter;
  private int ethCounter;
  private boolean deviceIsOn;

  private static final int INT_NUM_THREADS = 10;
  private static final int INT_MAX_ETHERNET = 1;
  private static final int INT_MAX_WLAN = 1;

  private Thread thread;
  private ExecutorService executor;

  @Inject
  public Laptop() {
    interfaces = new LinkedHashMap<>();
    protocols = new LinkedHashMap<>();
    outgoingQueue = new LinkedList<>();
    incomingQueue = new LinkedList<>();
    wlanCounter = 0;
    ethCounter = 0;
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
  public ArrayList<IHardwareInterface<? extends IFrame>> listInterfaces() {
    ArrayList<IHardwareInterface<? extends IFrame>> result;
    result = new ArrayList<>(interfaces.values());
    return result;
  }

  @Override
  public ArrayList<IHardwareInterface<? extends IFrame>> listWirelessInterfaces() {
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

  @Override
  public void process() {
    // TODO Auto-generated method stub

  }

  private void forward() {
  }

  @Override
  public <T1 extends IMessage> void receiveMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr) {
    enqueueIncoming(msg, "from");
  }

  @Override
  public <T1 extends IMessage> 
  void sendMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr) {
    //enqueueOutgoing(msg);
  }

  @Override
  public void addEthernetInterface(EthernetInterface eth) {
    return;
  }

  @Override
  public void addWlanInterface(WlanInterface wlan) {
    String id = "wlan" + wlanCounter;
    this.interfaces.put(id, wlan);
    wlanCounter++;
  }

  private void enqueueIncoming(IMessage data, String src) {
    DefaultKeyValue<String,IMessage> msg = new DefaultKeyValue<>(src, data);
    synchronized(incomingQueue) {
      try {
        incomingQueue.addLast(msg);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
  }

  private void enqueueOutgoing(IMessage data, String dest) {
    DefaultKeyValue<String,IMessage> msg = new DefaultKeyValue<>(dest, data);
    synchronized(outgoingQueue) {
      try {
        outgoingQueue.addLast(msg);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
  }

  private DefaultKeyValue<String,IMessage> dequeueIncoming() {
    DefaultKeyValue<String,IMessage> msg = null;
    synchronized(incomingQueue) {
      try {
        msg = incomingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
    return msg;
  }

  private DefaultKeyValue<String,IMessage> dequeueOutgoing() {
    DefaultKeyValue<String,IMessage> msg = null;
    synchronized(outgoingQueue) {
      try {
        msg = outgoingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
    return msg;
  }
   
  @Override
  public void run() {
    while(deviceIsOn) {
      process();
      forward();
    }

  }
}
