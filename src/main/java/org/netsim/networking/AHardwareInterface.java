package org.netsim.networking;

import java.util.ArrayList;

import org.netsim.device.ADevice;

public abstract class AHardwareInterface<T1 extends IPacket,T2 extends IFrame> 
  implements IProtocol<T1,T2>, Runnable {
  
  public abstract String getAddress();

  public abstract void addConsumer(IProtocol<? extends IDataUnit,T1> consumer);

  public abstract void addSource(IProtocol<T2,? extends IDataUnit> source);

  public abstract void send(IPacket payload, String dest, String source, String Protocol);

  public abstract void send(IPacket payload, String dest, String Protocol); 

  public abstract void receive(T2 message, IProtocol<T2,? extends IDataUnit> source);

  public abstract ArrayList<IProtocol<? extends IDataUnit,T1>> listConsumers();

  public abstract ArrayList<IProtocol<T2,? extends IDataUnit>> listSources();

  public abstract void setDevice(ADevice<IFrame> dev);

  public abstract void run();

  public abstract void setThread(Thread t); 
  
  public abstract <HW extends AHardwareInterface<?extends IPacket,?extends IFrame>>
  void setLink(ILink<? extends HW> link);
}
