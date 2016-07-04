package org.netsim.networking.hardware;

import java.util.ArrayList;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IPacket;
import org.netsim.networking.protocol.IProtocol;

public abstract class IHardwareInterface<T2 extends IFrame> 
  implements IProtocol<IPacket,T2>, Runnable {

  public abstract int getMaxHosts();

  public abstract double getCoverageDistance();

  public abstract String getAddress();

  public abstract void addConsumer(IProtocol<? extends IDataUnit,IPacket> consumer);

  public abstract void addSource(IProtocol<T2,? extends IDataUnit> source);

  public abstract void send(IPacket payload, String dest, String source, String Protocol);

  public abstract void send(IPacket payload, String dest, String Protocol); 

  public abstract void receive(T2 message, IProtocol<T2,? extends IDataUnit> source);

  public abstract ArrayList<IProtocol<? extends IDataUnit,IPacket>> listConsumers();

  public abstract ArrayList<IProtocol<T2,? extends IDataUnit>> listSources();

  public abstract void setDevice(IDevice<IFrame> dev);

  public abstract void run();

  public abstract void setThread(Thread t); 
  
  public abstract <I extends IHardwareInterface<T2>>
    void setLink(ILink<I> link);

  public abstract ArrayList<IHardwareInterface<T2>> getConnectedHosts();

  public abstract boolean isUp();

  public abstract boolean isLinkFull();

  public abstract ILink<? extends IHardwareInterface<T2>> getLink();
}
