package org.netsim.networking.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.netsim.networking.hardware.IHardwareInterface;

public class ForwardMacProtocol implements IMacProtocol {
  private HashMap<String,IHardwareInterface> interfaces;
  private HashMap<String,IProtocol<? extends IDataUnit, IPacket>> consumers;

  private LinkedList<DefaultKeyValue<IMacFrame,Properties>> outgoingQueue;

  private static final String STR_PROTOCOL_NAME = "MAC";
  private static final String STR_DESTINATION = "DST";
  private static final String STR_SOURCE = "SRC";

  private Thread thread;

  @Inject
  public ForwardMacProtocol() {
    interfaces = new HashMap<>();
    consumers = new HashMap<>();
    outgoingQueue = new LinkedList<>();
  }

  
  @Override
  public ArrayList<IProtocol<? extends IDataUnit, IPacket>> listConsumers() {
    ArrayList<IProtocol<? extends IDataUnit,IPacket>> result;
    result = new ArrayList<>(consumers.values());
    return result;
  }

  @Override
  public void setThread(Thread t) {
    if (thread == null)
      thread = t;
  }

  private void forward() {
    DefaultKeyValue<IMacFrame,Properties> entry = dequeueOutgoing();
    IMacFrame msg = entry.getKey();
    String source = msg.getSourceAddress();
    String dest = msg.getDestinationAddress();

    for (IHardwareInterface iface: interfaces.values()) {
      iface.send(msg, source, dest);
    }
  }

  @Override
  public void run() {
    while(true) {
      forward();
    }
  }

  @Override
  public void addInterface(IHardwareInterface iface) {
    try {
      interfaces.put(iface.getAddress(), iface);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  

  @Override
  public String getAddress() {
    return STR_PROTOCOL_NAME;
  }

  @Override
  public void addConsumer(IProtocol<? extends IDataUnit, IPacket> consumer) {
    try {
      consumers.put(consumer.getAddress(), consumer);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  @Override
  public void receive(IFrame msg) {
    IMacFrame frame = msg.getPayload();
    Properties props = new Properties();
    props.setProperty(STR_DESTINATION, msg.getDestinationAddress());
    props.setProperty(STR_SOURCE, msg.getSourceAddress());
    DefaultKeyValue<IMacFrame,Properties> entry = new DefaultKeyValue<>(frame, props);
    enqueueOutgoing(entry);
  }

  private DefaultKeyValue<IMacFrame,Properties> dequeueOutgoing() {
    DefaultKeyValue<IMacFrame,Properties> msg = null;
    synchronized(outgoingQueue) {
      try {
        msg = outgoingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
    return msg;
  }

  private void enqueueOutgoing(DefaultKeyValue<IMacFrame,Properties> msg) {
    synchronized(outgoingQueue) {
      try {
        outgoingQueue.addLast(msg);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
  }

  // The following methods are not going to be implemented, as they
  // are meant to be called by a consumer protocol and IMacProtocols
  // have no sources (technically).
  @Override
  public void send(IPacket payload, String dest, String consumer) {
    // TODO Auto-generated method stub

  }
  @Override
  public void receive(IMacFrame msg, String through) {
    return;
  }
  
  @Override
  public void send(IFrame frame) {
    return;
  }

  @Override
  public void addInterfaces(List<IHardwareInterface> ifaces) {
    // TODO Auto-generated method stub
    return;
  }

  @Override
  public ArrayList<IProtocol<IMacFrame, ? extends IDataUnit>> listSources() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void send(IPacket payload, String dest, String protocol, String through) {

  }

  @Override
  public void addSource(IProtocol<IMacFrame, ? extends IDataUnit> source) {
    return;
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

  
}
