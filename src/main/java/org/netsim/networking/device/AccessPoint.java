package org.netsim.networking.device;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IProtocolStack;

// TODO: The access point doesn't need to know about the content of the
// IFrame to send it out. The nature of the radio waves makes the WirelessLink
// object implement a broadcast mechanism.
public class AccessPoint implements IDevice {
  private LinkedHashMap<String,IHardwareInterface> interfaces;
  private IProtocolStack protocolStack;

  private int wlanCounter;
  private int ethCounter;
  private boolean deviceIsOn;

  private static final int INT_NUM_THREADS = 10;

  private Thread thread;
  private ExecutorService executor;

  @Inject
  public AccessPoint(IProtocolStack stack) {
    wlanCounter = 0;
    ethCounter = 0;
    deviceIsOn = true;
    thread = null;
    wlanCounter = 0;
    ethCounter = 0;
    interfaces = new LinkedHashMap<>(2);
    executor  = Executors.newFixedThreadPool(INT_NUM_THREADS);
    protocolStack = stack;
  }

  public void setProtocolStack(IProtocolStack stack) {
    protocolStack = stack;
  }

  @Override
  public ArrayList<IHardwareInterface> listInterfaces() {
    ArrayList<IHardwareInterface> result;
    result = new ArrayList<>(interfaces.values());
    return result;
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
  public ArrayList<IHardwareInterface> 
  listWirelessInterfaces() {
    ArrayList<IHardwareInterface> wlanList 
      = new ArrayList<>();

    for (Map.Entry<String,IHardwareInterface> wlan: interfaces.entrySet()) {

      if (wlan.getKey().contains("wlan")) {
        wlanList.add(wlan.getValue());
      }
    }
    return wlanList;
  }

  @Override
  public void addProtocolStack() {
    // TODO Auto-generated method stub

  }

  @Override
  public void process() {
    // TODO Auto-generated method stub

  }

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

  @Override
  public void addEthernetInterface(IHardwareInterface eth) {
    String id = "eth" + ethCounter;
    this.interfaces.put(id, eth);
    protocolStack.addInterface(eth);
    ethCounter++;
  }

  @Override
  public void addWlanInterface(IHardwareInterface wlan) {
    String id = "wlan" + wlanCounter;
    this.interfaces.put(id, wlan);
    wlan.setDevice(this);
    protocolStack.addInterface(wlan);
    wlanCounter++;

  }

}
