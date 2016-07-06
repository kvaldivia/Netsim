package org.netsim.networking.hardware;

import java.util.ArrayList;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IMacFrame;
import org.netsim.networking.protocol.IMacProtocol;

public abstract class IHardwareInterface {

  public abstract int getMaxHosts();

  public abstract double getCoverageDistance();

  public abstract String getAddress();

  public abstract void send(IMacFrame payload, String dest, String source);

  public abstract void send(IMacFrame payload, String dest); 

  public abstract void receive(IFrame message);

  public abstract void setDevice(IDevice dev);

  public abstract IDevice getDevice();

  public abstract void run();

  public abstract void setThread(Thread t); 
  
  public abstract void setLink(ILink<? extends IHardwareInterface> link);

  public abstract ArrayList<IHardwareInterface> getConnectedHosts();

  public abstract boolean isUp();

  public abstract boolean isLinkFull();

  public abstract ILink<? extends IHardwareInterface> getLink();

  public abstract void setMacProtocol(IMacProtocol mac);
}
