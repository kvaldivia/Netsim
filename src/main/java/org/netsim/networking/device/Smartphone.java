package org.netsim.networking.device;

import java.util.ArrayList;

import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IMessage;
import org.netsim.networking.protocol.IProtocol;

public class Smartphone implements IConsumerDevice {

  @Override
  public ArrayList<IHardwareInterface<? extends IFrame>> listConnected() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<IHardwareInterface<? extends IFrame>> listInterfaces() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<IHardwareInterface<? extends IFrame>> listWirelessInterfaces() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void process() {
    // TODO Auto-generated method stub

  }

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

  @Override
  public void addEthernetInterface(EthernetInterface eth) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addWlanInterface(WlanInterface wlan) {
    // TODO Auto-generated method stub

  }

  @Override
  public <T1 extends IMessage> void receiveMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr) {
    // TODO Auto-generated method stub

  }

  @Override
  public <T1 extends IMessage> void sendMessage(T1 msg, IProtocol<? extends IDataUnit, T1> thr) {
    // TODO Auto-generated method stub

  }

}
