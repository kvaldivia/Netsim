package org.netsim.networking.device;

import java.util.ArrayList;

import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.protocol.IMessage;
import org.netsim.networking.protocol.IProtocolStack;

public class Smartphone implements IConsumerDevice {

  @Override
  public ArrayList<IHardwareInterface> listConnected() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<IHardwareInterface> listInterfaces() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<IHardwareInterface> listWirelessInterfaces() {
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
  public void addProtocolStack() {
    // TODO Auto-generated method stub

  }

  @Override
  public void receiveMessage(IMessage msg, String from) {
    // TODO Auto-generated method stub

  }

  @Override
  public void sendMessage(IMessage msg, String from) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addEthernetInterface(IHardwareInterface eth) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addWlanInterface(IHardwareInterface wlan) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setProtocolStack(IProtocolStack stack) {
    // TODO Auto-generated method stub

  }

}
