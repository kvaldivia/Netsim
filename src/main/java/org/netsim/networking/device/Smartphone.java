package org.netsim.networking.device;

import java.util.ArrayList;

import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IProtocol;
import org.netsim.networking.protocol.Message;

public class Smartphone extends AConsumerDevice {

  @Override
  public ArrayList<AHardwareInterface<? extends IFrame>> listConnected() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<AHardwareInterface<? extends IFrame>> listInterfaces() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<AHardwareInterface<? extends IFrame>> listWirelessInterfaces() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void process() {
    // TODO Auto-generated method stub

  }

  @Override
  public <T1 extends Message> void receiveMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr) {
    // TODO Auto-generated method stub

  }

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

  @Override
  public <T1 extends Message> void sendMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr) {
    // TODO Auto-generated method stub

  }

}
