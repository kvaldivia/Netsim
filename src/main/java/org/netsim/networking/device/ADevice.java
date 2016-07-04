package org.netsim.networking.device;

import java.util.ArrayList;

import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IProtocol;
import org.netsim.networking.hardware.AHardwareInterface;

public abstract class ADevice<T extends IDataUnit> implements Runnable{
  public abstract <T1 extends T>
    void receiveMessage(T1 msg, IProtocol<? extends IDataUnit,T1> thr);
  
  public abstract <T1 extends T>
    void sendMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr);

  public abstract 
    ArrayList<AHardwareInterface<?extends IFrame>> listInterfaces();

  public abstract 
    ArrayList<AHardwareInterface<?extends IFrame>> listWirelessInterfaces();

  public abstract
    ArrayList<AHardwareInterface<?extends IFrame>> listConnected();

  public abstract void process();

  public abstract void run();
}
