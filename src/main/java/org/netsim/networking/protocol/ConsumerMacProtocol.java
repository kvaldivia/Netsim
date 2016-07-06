package org.netsim.networking.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import org.netsim.networking.hardware.IHardwareInterface;

public class ConsumerMacProtocol implements IMacProtocol {
  private HashMap<String, IHardwareInterface> interfaces;
  private HashMap<String, IProtocol<? extends IDataUnit,IPacket>> consumers;

  private LinkedList<DefaultKeyValue<IMacFrame,Properties>> outgoingQueue;
  private LinkedList<DefaultKeyValue<IMacFrame,Properties>> incomingQueue;

  private Thread thread;
  private static final String STR_PROTOCOL_NAME = "MAC";
  private static final String STR_DESTINATION = "DST";
  private static final String STR_THROUGH = "THR";

  @Inject
  public ConsumerMacProtocol() {
    interfaces = new HashMap<>();
    consumers = new HashMap<>();
    outgoingQueue = new LinkedList<>();
    incomingQueue = new LinkedList<>();
  }

  @Override
  public String getAddress() {
    return STR_PROTOCOL_NAME;
  }

  @Override
  public void addConsumer(IProtocol<? extends IDataUnit,IPacket> consumer) {
    try {
      consumers.put(consumer.getAddress(), consumer);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
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

  private void process() {
    DefaultKeyValue<IMacFrame,Properties> entry = dequeueIncoming();
    IMacFrame msg = entry.getKey();
    Properties props = entry.getValue();
    String through = props.getProperty(STR_THROUGH);

    String destination = msg.getDestinationAddress();
    IProtocol<? extends IDataUnit, IPacket> protocol;
    protocol = consumers.get(destination);

    if (protocol != null) {
      protocol.receive(msg.getPayload(), through);
    }

  }

  private void forward() {
    DefaultKeyValue<IMacFrame,Properties> entry = dequeueOutgoing();
    String throughId = entry.getValue().getProperty(STR_THROUGH);
    String destination = entry.getValue().getProperty(STR_DESTINATION);
    IMacFrame msg = entry.getKey();
    IHardwareInterface iface = interfaces.get(throughId);

    iface.send(msg, destination);
  }

  @Override
  public void receive(IMacFrame msg, String through) {
    Properties props = new Properties();
    props.setProperty(STR_THROUGH, through);
    DefaultKeyValue<IMacFrame,Properties> entry = new DefaultKeyValue<>(msg, props);
    enqueueIncoming(entry);
  }

  @Override
  public void run() {
    while(true) {
      process();
      forward();
    }
  }

  private DefaultKeyValue<IMacFrame,Properties> dequeueIncoming() {
    DefaultKeyValue<IMacFrame,Properties> msg = null;
    synchronized(incomingQueue) {
      try {
        msg = incomingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
    return msg;
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

  private void enqueueIncoming(DefaultKeyValue<IMacFrame,Properties> msg) {
    synchronized(outgoingQueue) {
      try {
        outgoingQueue.addLast(msg);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
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

  @Override
  public void addInterface(IHardwareInterface iface) {
    try {
      interfaces.put(iface.getAddress(), iface);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  @Override
  public void addInterfaces(List<IHardwareInterface> ifaces) {
    // TODO Auto-generated method stub

  }

  @Override
  public void send(IPacket payload, String dest, String protocol, String through) {
    DefaultKeyValue<IMacFrame,Properties> entry;
    Properties props = new Properties();
    props.setProperty(STR_DESTINATION, dest);
    props.setProperty(STR_THROUGH, through);
    IMacFrame msg = new MacFrame(payload, protocol, protocol);
    entry = new DefaultKeyValue<>(msg, props);
    enqueueOutgoing(entry);
  }

  @Override
  public void receive(IFrame msg) {
    // TODO Auto-generated method stub

  }


  // useless methods
  @Override
  public ArrayList<IProtocol<IMacFrame, ? extends IDataUnit>> listSources() {
    // TODO Auto-generated method stub
    return null;
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
  public void send(IFrame frame) {

  }
  
  @Override
  public void addSource(IProtocol<IMacFrame, ? extends IDataUnit> source) {
    return;
  }

  @Override
  public void send(IPacket payload, String dest, String consumer) {
    // TODO Auto-generated method stub

  }
}
