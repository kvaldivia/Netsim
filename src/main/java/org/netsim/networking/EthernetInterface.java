package org.netsim.networking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.inject.Inject;

import org.netsim.device.ADevice;

public class EthernetInterface extends AHardwareInterface<IPacket,EthernetFrame> {
  private String address;
  private HashMap<String,IProtocol<?extends IDataUnit,IPacket>> consumers;
  private LinkedList<EthernetFrame> outgoingQueue;
  private LinkedList<EthernetFrame> incomingQueue;
  private ADevice<IFrame> device;
  private boolean isInterfaceUp;
  private WiredLink link;
  
  private Thread thread;

  @Inject
  public EthernetInterface() {
    address = MacAddressGenerator.getInstance().newMacAddress();
    consumers = new HashMap<>();
    outgoingQueue = new LinkedList<>();
    incomingQueue = new LinkedList<>();
    device = null;
    isInterfaceUp = true;
    thread = null;
  }

  public void setThread(Thread t) {
    if (thread == null)
      thread = t;
    else
      System.out.println("Thread already asigned: " + thread.getName());
  }

  public String getAddress() {
    return this.address;
  }

  public void addConsumer(IProtocol<? extends IDataUnit,IPacket> consumer) {
    this.consumers.put(consumer.getAddress(), consumer);
  }

  public ArrayList<IProtocol<? extends IDataUnit,IPacket>> listConsumers() {
    return new ArrayList<>(this.consumers.values());
  }

  public void addSource(IProtocol<EthernetFrame,? extends IDataUnit> source) {
    return;
  }

  public ArrayList<IProtocol<EthernetFrame,? extends IDataUnit>> listSources() {
    return null;
  }

  public synchronized void receive(EthernetFrame frame
                                   , IProtocol<EthernetFrame, ? extends IDataUnit> source) {

    synchronized(incomingQueue) {
      incomingQueue.addLast(frame);
    }
  }

  public void send(IPacket payload, String dest, String protocol) {
    EthernetFrame frame = wrap(payload, dest, address, protocol);
    synchronized(outgoingQueue) {
      outgoingQueue.addLast(frame);
    }
  }

  public void send(IPacket payload, String dest, String source, String protocol) {
    EthernetFrame frame = wrap(payload, dest, address, protocol);
    synchronized(outgoingQueue) {
      outgoingQueue.addLast(frame);
    }
  }

  public EthernetFrame wrap(IPacket payload, String src, String dest, String prot) {
    return new EthernetFrame(payload, src, dest, prot);
  }

  public EthernetFrame wrap(IPacket payload, String dest, String prot) {
    return new EthernetFrame(payload, this.address, dest, prot);
  }

  public IPacket unwrap(EthernetFrame frame) {
    return frame.getPayload();
  }

  private EthernetFrame dequeueIncoming() {
    synchronized(incomingQueue) {
      try {
        return incomingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
      return null;
    }
  }

  private EthernetFrame dequeueOutgoing() {
    synchronized(outgoingQueue) {
      try {
        return outgoingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      } 
      return null;
    }
  }

  private void forward() {
    EthernetFrame msg = dequeueOutgoing();
    msg.toString();
    //link.transmit(msg);
  }

  private IProtocol<?extends IDataUnit,IPacket> getConsumer(String address) {
    try {
      return consumers.get(address);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
    return null;
  }

  private void process() {
    EthernetFrame frame = dequeueIncoming();

    if (frame != null) {
      String destination = frame.getDestinationAddress();
      boolean addressMatch = (destination == this.address);

      if (!consumers.isEmpty() && addressMatch) {
        String protocol = frame.getProtocol();
        IProtocol<? extends IDataUnit,IPacket> consumer = getConsumer(protocol);

        if (consumer != null) {
          consumer.receive(frame.getPayload(), this);
        }
   
      } else if (device != null) {
        device.receiveMessage(frame, this);
      }
    }
  }

  public void run() {
    while (isInterfaceUp) {
      forward();
      process();
    }
  }

  public EthernetFrame wrap(IPacket payload, String destAddress) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setDevice(ADevice<IFrame> dev) {
    if (device == null) {
      device = dev;
    }
  }

  @Override
  public <HW extends AHardwareInterface<? extends IPacket, ? extends IFrame>> 
  void setLink(ILink<? extends HW> ethLink) {
    if (link == null) {
      link = (WiredLink) ethLink;
    }
  }

}
