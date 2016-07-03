package org.netsim.networking;

import java.util.ArrayList;

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
