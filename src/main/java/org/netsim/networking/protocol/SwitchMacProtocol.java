package org.netsim.networking.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.netsim.networking.hardware.IHardwareInterface;

public class SwitchMacProtocol implements IMacProtocol {
  private HashMap<String, IHardwareInterface> interfaces;
  private HashMap<String, IProtocol<? extends IDataUnit,? extends IPacket>> consumers;

  private LinkedList<EthernetFrame> outgoingQueue;
  private LinkedList<EthernetFrame> incomingQueue;

  private Thread thread;

  @Inject
  public SwitchMacProtocol() {
    interfaces = new HashMap<>();
    consumers = new HashMap<>();
    incomingQueue = new LinkedList<>();
    outgoingQueue = new LinkedList<>();
  }

  @Override
  public void addInterface(IHardwareInterface iface) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addInterfaces(List<IHardwareInterface> ifaces) {
    // TODO Auto-generated method stub

  }

  @Override
  public void send(IPacket payload, String dest, String protocol, String through) {
    // TODO Auto-generated method stub

  }

  @Override
  public void receive(IMacFrame msg, String through) {
    // TODO Auto-generated method stub

  }

  @Override
  public void receive(IFrame msg) {
    // TODO Auto-generated method stub

  }

  @Override
  public void send(IFrame frame) {
    // TODO Auto-generated method stub

  }

  @Override
  public IMacFrame wrap(IPacket payload, String dest, String consumer) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IMacFrame wrap(IPacket payload, String source, String dest, String consumer) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getAddress() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addConsumer(IProtocol<? extends IDataUnit, IPacket> consumer) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addSource(IProtocol<IMacFrame, ? extends IDataUnit> source) {
    // TODO Auto-generated method stub

  }

  @Override
  public void send(IPacket payload, String dest, String consumer) {
    // TODO Auto-generated method stub

  }

  @Override
  public ArrayList<IProtocol<? extends IDataUnit, IPacket>> listConsumers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<IProtocol<IMacFrame, ? extends IDataUnit>> listSources() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setThread(Thread t) {
    // TODO Auto-generated method stub

  }

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

}
