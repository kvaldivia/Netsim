package org.netsim.networking.device;

import java.util.ArrayList;

import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IProtocol;
import org.netsim.networking.protocol.Message;

public abstract class AConsumerDevice extends ADevice<Message> {

  public abstract ArrayList<AHardwareInterface<? extends IFrame>> listConnected();
    
  public abstract ArrayList<AHardwareInterface<? extends IFrame>> listInterfaces();

  public abstract ArrayList<AHardwareInterface<? extends IFrame>> listWirelessInterfaces();

  public abstract void process();

  public abstract <T1 extends Message> void receiveMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr);

  public abstract void run();

  public abstract <T1 extends Message> void sendMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr);
}
