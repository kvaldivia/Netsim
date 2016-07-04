package org.netsim.networking.device;

import java.util.ArrayList;

import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IMessage;

public interface IConsumerDevice extends IDevice<IMessage> {

  public ArrayList<IHardwareInterface<? extends IFrame>> listConnected();
    
  public ArrayList<IHardwareInterface<? extends IFrame>> listInterfaces();

  public ArrayList<IHardwareInterface<? extends IFrame>> listWirelessInterfaces();

  public void process();

  public void run();
}
