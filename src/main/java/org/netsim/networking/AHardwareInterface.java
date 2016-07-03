package org.netsim.networking;

import java.util.ArrayList;

public abstract class AHardwareInterface<T2 extends IFrame> 
  implements IProtocol<IPacket,T2>, Runnable {

  public final int MAX_HOSTS = 0;
  
  public abstract String getAddress();

  public abstract void addConsumer(IProtocol<? extends IDataUnit,IPacket> consumer);

  public abstract void addSource(IProtocol<T2,? extends IDataUnit> source);

  public abstract void send(IPacket payload, String dest, String source, String Protocol);

  public abstract void send(IPacket payload, String dest, String Protocol); 

  public abstract void receive(T2 message, IProtocol<T2,? extends IDataUnit> source);

  public abstract ArrayList<IProtocol<? extends IDataUnit,IPacket>> listConsumers();

  public abstract ArrayList<IProtocol<T2,? extends IDataUnit>> listSources();

  public abstract void setDevice(ADevice<IFrame> dev);

  public abstract void run();

  public abstract void setThread(Thread t); 
  
  public abstract <I extends AHardwareInterface<T2>>
    void setLink(ILink<I> link);

  public abstract ArrayList<AHardwareInterface<T2>> getConnectedHosts();

  public abstract boolean isUp();

  public abstract boolean isLinkFull();
}
