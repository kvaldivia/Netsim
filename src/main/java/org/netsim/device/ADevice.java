package org.netsim.device;

import java.util.ArrayList;

import org.netsim.networking.IDataUnit;
import org.netsim.networking.IProtocol;
import org.netsim.networking.AHardwareInterface;
import org.netsim.networking.IFrame;
import org.netsim.networking.IPacket;

public abstract class ADevice<T extends IDataUnit> implements Runnable{
  public abstract <T1 extends T>
    void receiveMessage(T1 msg, IProtocol<? extends IDataUnit,T1> thr);
  
  public abstract <T1 extends T>
    void sendMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr);

  public abstract 
    ArrayList<AHardwareInterface<?extends IPacket,?extends IFrame>> listInterfaces();

  public abstract
    ArrayList<AHardwareInterface<?extends IPacket,?extends IFrame>> listConnected();

  public abstract void process();

  public abstract void run();
}
