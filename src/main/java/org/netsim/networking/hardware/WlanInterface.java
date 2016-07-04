package org.netsim.networking.hardware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.inject.Inject;

import org.netsim.networking.device.ADevice;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.ILink;
import org.netsim.networking.protocol.IPacket;
import org.netsim.networking.protocol.IProtocol;
import org.netsim.networking.protocol.WlanFrame;

public class WlanInterface extends AHardwareInterface<WlanFrame> {
  private String address;
  private String gatewayAddress;
  private boolean up;

  private HashMap<String,IProtocol<? extends IDataUnit,IPacket>> consumers;
  private LinkedList<WlanFrame> incoming;
  private LinkedList<WlanFrame> outgoing;
  private ADevice<IFrame> device;
  private Thread thread;
  private WirelessLink wlanLink;

  public final double DOUBLE_COVERAGE_DISTANCE;
  public final int INT_MAX_HOSTS;

  @Inject
  public WlanInterface() {
    DOUBLE_COVERAGE_DISTANCE = 300;
    INT_MAX_HOSTS = 30;
    address = MacAddressGenerator.getInstance().newMacAddress();
    consumers = new HashMap<>();
    incoming = new LinkedList<>();
    device = null;
    thread = null;
    gatewayAddress = null;
    wlanLink = null;
    up = true;
  }

  public WlanInterface(int coverage, int hosts) {
    DOUBLE_COVERAGE_DISTANCE = coverage;
    INT_MAX_HOSTS = hosts;
    address = MacAddressGenerator.getInstance().newMacAddress();
    consumers = new HashMap<>();
    incoming = new LinkedList<>();
    device = null;
    thread = null;
    gatewayAddress = null;
    wlanLink = null;
    up = true;
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
    while (this.up) {
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
  public <I extends AHardwareInterface<WlanFrame>> void setLink(ILink<I> link) {
    if (wlanLink == null) {
      wlanLink = (WirelessLink) link;
    }
  }

  @Override
  public boolean isUp() {
    return up;
  }

  @Override
  public ArrayList<AHardwareInterface<WlanFrame>> getConnectedHosts() {
    ArrayList<AHardwareInterface<WlanFrame>> result = new ArrayList<>();
    if (wlanLink != null) {
      result.addAll(wlanLink.getHosts());
    }
    return result;
  }

  @Override
  public boolean isLinkFull() {
    boolean result = false;
    if (wlanLink != null && wlanLink.isLinkFull())
      result = true;
    return result;
  }

}
