package org.netsim.networking; 
import java.util.ArrayList;

public class Internet implements IProtocol<Message,IPacket> {
  private static final String address = "IP";
  private ArrayList<IProtocol<? extends IDataUnit,Message>> consumers;
  private ArrayList<IProtocol<IPacket,? extends IDataUnit>> sources;


  public Internet() {
    consumers = new ArrayList<>();
    sources = new ArrayList<>();
  }

  public IPacket wrap(Message payload, String destAddress) {
    return null;
  }

  public Message unwrap(IPacket pdu) {
    return null;
  }

  public void addConsumer(IProtocol<? extends IDataUnit,Message> consumer) {
    this.consumers.add(consumer);
  }

  public void addSource(IProtocol<IPacket,? extends IDataUnit> source) {
    this.sources.add(source);
  }

  public void send(Message payload, String address) {
    // TODO: implementation

  }

  public void receive(IPacket message, IProtocol<IPacket, ? extends IDataUnit> source) {
    // TODO: implementation
  }

  public ArrayList<IProtocol<? extends IDataUnit,Message>> getConsumers() {
    return this.consumers;
  }

  public ArrayList<IProtocol<IPacket,? extends IDataUnit>> getSources() {
    return this.sources;
  }

  public String getAddress() {
    return address;
  }

  public void run() {

  }

  @Override
  public ArrayList<IProtocol<? extends IDataUnit, Message>> listConsumers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<IProtocol<IPacket, ? extends IDataUnit>> listSources() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void send(Message payload, String source, String dest) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setThread(Thread t) {
    // TODO Auto-generated method stub

  }

  @Override
  public void send(Message payload, String source, String dest, String consumer) {
    // TODO Auto-generated method stub

  }

  @Override
  public IPacket wrap(Message payload, String dest, String consumer) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IPacket wrap(Message payload, String source, String dest, String consumer) {
    // TODO Auto-generated method stub
    return null;
  }
}
