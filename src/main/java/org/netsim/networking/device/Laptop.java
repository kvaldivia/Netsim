package org.netsim.networking.device;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.protocol.IMessage;
import org.netsim.networking.protocol.IProtocolStack;

public class Laptop implements IConsumerDevice {
  private LinkedHashMap<String,IHardwareInterface> interfaces;
  private LinkedList<DefaultKeyValue<String,IMessage>> outgoingQueue;
  private LinkedList<DefaultKeyValue<String,IMessage>> incomingQueue;
  private IProtocolStack protocolStack;

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
    outgoingQueue = new LinkedList<>();
    incomingQueue = new LinkedList<>();
    wlanCounter = 0;
    ethCounter = 0;
  }

  @Override
  public ArrayList<IHardwareInterface> listConnected() {
    ArrayList<IHardwareInterface> connected;
    connected = new ArrayList<>();
    for (IHardwareInterface iface: interfaces.values()) {
      connected.addAll(iface.getConnectedHosts());
    }
    return connected;
  }

  @Override
  public ArrayList<IHardwareInterface> listInterfaces() {
    ArrayList<IHardwareInterface> result;
    result = new ArrayList<>(interfaces.values());
    return result;
  }

  @Override
  public ArrayList<IHardwareInterface> listWirelessInterfaces() {
    ArrayList<IHardwareInterface> wlanList 
      = new ArrayList<>();

    for (Map.Entry<String,IHardwareInterface> wlan
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
  public void run() {
    while(deviceIsOn) {
      process();
      forward();
    }

  }

  @Override
  public void addProtocolStack() {

  }

  @Override
  public void receiveMessage(IMessage msg, String from) {
    // TODO Auto-generated method stub

  }

  @Override
  public void sendMessage(IMessage msg, String from) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addEthernetInterface(IHardwareInterface eth) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addWlanInterface(IHardwareInterface wlan) {
    String id = "wlan" + wlanCounter;
    this.interfaces.put(id, wlan);
    wlanCounter++;
  }

  @Override
  public void setProtocolStack(IProtocolStack stack) {
    // TODO Auto-generated method stub

  }
}
