package org.netsim.networking;

import org.netsim.device.ADevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.inject.Inject;

public class WlanInterface extends AHardwareInterface<IPacket,WlanFrame> {
  private String address;
  private String gatewayAddress;
  private boolean isUp;
  //private WirelessLink wlanLink;

  private HashMap<String,IProtocol<? extends IDataUnit,IPacket>> consumers;
  private LinkedList<WlanFrame> incoming;
  private LinkedList<WlanFrame> outgoing;
  private ADevice<IFrame> device;
  private Thread thread;
  private WirelessLink wlanLink;

  @Inject
  public WlanInterface() {
    isUp = true;
    address = MacAddressGenerator.getInstance().newMacAddress();
    consumers = new HashMap<>();
    incoming = new LinkedList<>();
    device = null;
    thread = null;
    gatewayAddress = null;
    wlanLink = null;
  }

  public String getAddress() {
    return this.address;
  }

  public void addConsumer(IProtocol<? extends IDataUnit,IPacket> consumer) {
    this.consumers.put(consumer.getAddress(), consumer);
  }

  public ArrayList<IProtocol<? extends IDataUnit,IPacket>> getConsumers() {
    return null;
  }

  public void addSource(IProtocol<WlanFrame, ? extends IDataUnit> source) {
    return;
  }

  public ArrayList<IProtocol<WlanFrame,? extends IDataUnit>> getSources() {
    return null;
  }

  public void receive(WlanFrame frame,
                      IProtocol<WlanFrame,? extends IDataUnit> source) {
    synchronized(incoming) {
      this.incoming.addLast(frame);
    }
  }

  private WlanFrame dequeueIncoming() {
    synchronized(incoming) {
      try {
        return incoming.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
      return null;
    }
  }

  private WlanFrame dequeueOutgoing() {
    synchronized(outgoing) {
      try {
        return outgoing.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
      return null;
    }
  }

  private IProtocol<? extends IDataUnit,IPacket> getConsumer(String key) {
    try {
      return consumers.get(key);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
    return null;
  }

  private void process() {
    WlanFrame frame = dequeueIncoming();

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
  
  public void send(IPacket payload, String dest, String protocol) {
    WlanFrame frame = wrap(payload, this.address, dest, protocol);
    synchronized(outgoing) {
      this.outgoing.addLast(frame);
    }
  }

  public void send(IPacket payload, String source, String dest, String protocol) {
    WlanFrame frame = wrap(payload, source, dest, protocol);
    synchronized(outgoing) {
      this.outgoing.addLast(frame);
    }
  }

  private void forward() {
    WlanFrame frame = dequeueOutgoing();
    if (frame == null)
      return;
    //wlanLink.transmit(fw);
  }

  public WlanFrame wrap(IPacket payload, String source, String dest, String prot) {
    return new WlanFrame(payload, source, dest, gatewayAddress, prot);
  }

  public IPacket unwrap(WlanFrame frame) {
    return frame.getPayload();
  }

  public void run() {
    while (isUp) {
      forward();
      process();
    }
  }

  @Override
  public ArrayList<IProtocol<? extends IDataUnit, IPacket>> listConsumers() {
    return new ArrayList<>(this.consumers.values());
  }

  @Override
  public ArrayList<IProtocol<WlanFrame, ? extends IDataUnit>> listSources() {
    // TODO Auto-generated method stub
    return null;
  }

  public void setThread(Thread t) {
    if (thread == null) {
      thread = t;
    }
  }

  @Override
  public WlanFrame wrap(IPacket payload, String dest, String consumer) {
    return wrap(payload, this.address, dest, consumer);
  }

  @Override
  public void setDevice(ADevice<IFrame> dev) {
    if(device == null) {
      device = dev;
    }
  }

  @Override
  public <HW extends AHardwareInterface<? extends IPacket, ? extends IFrame>> 
  void setLink(ILink<? extends HW> link) {
    if (wlanLink == null) {
      wlanLink = (WirelessLink) link;
    }
  }

 }
