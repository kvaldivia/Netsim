package org.netsim.networking.hardware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.inject.Inject;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.protocol.EthernetFrame;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IPacket;
import org.netsim.networking.protocol.IProtocol;

public class EthernetInterface extends IHardwareInterface<EthernetFrame> {
  private String address;
  private HashMap<String,IProtocol<?extends IDataUnit,IPacket>> consumers;
  private LinkedList<EthernetFrame> outgoingQueue;
  private LinkedList<EthernetFrame> incomingQueue;
  private IDevice<IFrame> device;
  private boolean up;
  private WiredLink wiredLink;
  private Thread thread;

  public final double DOUBLE_COVERAGE_DISTANCE = 0;
  public final int INT_MAX_HOSTS = 2;

  @Inject
  public EthernetInterface() {
    address = MacAddressGenerator.getInstance().newMacAddress();
    consumers = new HashMap<>();
    outgoingQueue = new LinkedList<>();
    incomingQueue = new LinkedList<>();
    device = null;
    up = true;
    thread = null;
    wiredLink = null;
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
    try {
      wiredLink.transmit(msg, this);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
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
    while (this.up) {
      forward();
      process();
    }
  }

  public EthernetFrame wrap(IPacket payload, String destAddress) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setDevice(IDevice<IFrame> dev) {
    if (device == null) {
      device = dev;
    }
  }

  @Override
  public <I extends IHardwareInterface<EthernetFrame>> void setLink(ILink<I> link) {
    // TODO Auto-generated method stub
    if (wiredLink == null) {
      wiredLink = (WiredLink) link;
    }
  }

  @Override
  public ArrayList<IHardwareInterface<EthernetFrame>> getConnectedHosts() {
    ArrayList<IHardwareInterface<EthernetFrame>> result = new ArrayList<>();

    if (wiredLink != null) {
      result.addAll(wiredLink.getHosts());
    }
    return result;
  }

  @Override
  public boolean isUp() {
    return up;
  }

  @Override
  public boolean isLinkFull() {
    boolean result = false;
    if (wiredLink != null && wiredLink.isLinkFull())
      result = true;
    return result;
  }

  @Override
  public ILink<? extends IHardwareInterface<EthernetFrame>> getLink() {
    return wiredLink;
  }

  @Override
  public double getCoverageDistance() {
    return DOUBLE_COVERAGE_DISTANCE;
  }

  @Override
  public int getMaxHosts() {
    return INT_MAX_HOSTS;
  }
}
