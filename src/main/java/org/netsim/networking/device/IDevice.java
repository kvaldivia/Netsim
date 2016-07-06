package org.netsim.networking.device;

import java.util.ArrayList;

import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.protocol.IProtocolStack;

public interface IDevice extends Runnable{

  public ArrayList<IHardwareInterface> listInterfaces();

  public ArrayList<IHardwareInterface> listWirelessInterfaces();

  public ArrayList<IHardwareInterface> listConnected();

  public void process();

  public void run();

  public void addEthernetInterface(IHardwareInterface eth);
  
  public void addWlanInterface(IHardwareInterface wlan);

  public void addProtocolStack();

  void setProtocolStack(IProtocolStack stack);
}
