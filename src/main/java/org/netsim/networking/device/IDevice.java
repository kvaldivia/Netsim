package org.netsim.networking.device;

import java.util.ArrayList;

import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IProtocol;

public interface IDevice<T extends IDataUnit> extends Runnable{
  public <T1 extends T>
    void receiveMessage(T1 msg, IProtocol<? extends IDataUnit,T1> thr);
  
  public <T1 extends T>
    void sendMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr);

  public ArrayList<IHardwareInterface<?extends IFrame>> listInterfaces();

  public ArrayList<IHardwareInterface<?extends IFrame>> listWirelessInterfaces();

  public ArrayList<IHardwareInterface<?extends IFrame>> listConnected();

  public void process();

  public void run();

  public void addEthernetInterface(EthernetInterface eth);
  
  public void addWlanInterface(WlanInterface wlan);
}
