package org.netsim.networking.protocol; 
import java.util.ArrayList;
import java.util.LinkedList;

import javax.inject.Inject;

public class InternetProtocol implements INetworkProtocol {
  private static final String address = "IP";
  private ArrayList<IProtocol<? extends IDataUnit,Message>> consumers;
  private ArrayList<IProtocol<IPacket,? extends IDataUnit>> sources;
  
  private LinkedList<IpPacket> incomingQueue;
  private LinkedList<IpPacket> outgoingQueue;

  @Inject
  public InternetProtocol() {
    consumers = new ArrayList<>();
    sources = new ArrayList<>();
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public void run() {

  }

  @Override
  public void setThread(Thread t) {
    // TODO Auto-generated method stub

  }

  @Override
  public IPacket wrap(IMessage payload, String dest, String consumer) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IPacket wrap(IMessage payload, String source, String dest, String consumer) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void send(IMessage payload, String dest, String consumer) {
    // TODO Auto-generated method stub

  }


  @Override
  public void receive(IPacket msg, String through) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addConsumer(IProtocol<? extends IDataUnit, IMessage> consumer) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addSource(IProtocol<IPacket, ? extends IDataUnit> source) {
    // TODO Auto-generated method stub

  }

  @Override
  public ArrayList<IProtocol<? extends IDataUnit, IMessage>> listConsumers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<IProtocol<IPacket, ? extends IDataUnit>> listSources() {
    // TODO Auto-generated method stub
    return null;
  }

  private IpPacket dequeueIncoming() {
    IpPacket msg = null;
    synchronized(incomingQueue) {
      try {
        msg = incomingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
    return msg;
  }

  private IpPacket dequeueOutgoing() {
    IpPacket msg = null;
    synchronized(outgoingQueue) {
      try {
        msg = outgoingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
    return msg;
  }

  private void enqueueIncoming(IpPacket msg) {
    synchronized(outgoingQueue) {
      try {
        outgoingQueue.addLast(msg);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
  }

  private void enqueueOutgoing(IpPacket msg) {
    synchronized(outgoingQueue) {
      try {
        outgoingQueue.addLast(msg);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
  }


}
