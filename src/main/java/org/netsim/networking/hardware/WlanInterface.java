package org.netsim.networking.hardware;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.inject.Inject;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IMacFrame;
import org.netsim.networking.protocol.IMacProtocol;
import org.netsim.networking.protocol.WlanFrame;

public class WlanInterface extends IHardwareInterface {
  private String address;
  private String gatewayAddress;
  private boolean up;

  private LinkedList<IFrame> incomingQueue;
  private LinkedList<IFrame> outgoingQueue;
  private IDevice device;
  private Thread thread;
  private WirelessLink wlanLink;
  private IMacProtocol macProtocol;

  private final int INT_MAX_HOSTS;
  private final double DOUBLE_COVERAGE_DISTANCE;

  @Inject
  public WlanInterface() {
    DOUBLE_COVERAGE_DISTANCE = 300;
    INT_MAX_HOSTS = 30;
    address = MacAddressGenerator.getInstance().newMacAddress();
    incomingQueue = new LinkedList<>();
    outgoingQueue = new LinkedList<>();
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
    incomingQueue = new LinkedList<>();
    outgoingQueue = new LinkedList<>();
    device = null;
    thread = null;
    gatewayAddress = null;
    wlanLink = null;
    up = true;
  }

  public String getAddress() {
    return this.address;
  }

  private void process() {
    IFrame msg = dequeueIncoming();

    if (msg == null || macProtocol != null) 
      return;
    
    String destination = msg.getDestinationAddress();
    boolean addressMatch = (destination == this.address);

    if (addressMatch) {
      macProtocol.receive(msg.getPayload(), this.address);
    } else {
      macProtocol.receive(msg);
    }
    
  }
  
  @Override
  public void send(IMacFrame payload, String dest) {
    WlanFrame frame = new WlanFrame(payload, this.address, dest, gatewayAddress);
    synchronized(outgoingQueue) {
      this.outgoingQueue.addLast(frame);
    }
  }

  @Override
  public void send(IMacFrame payload, String source, String dest) {
    WlanFrame frame = new WlanFrame(payload, source, dest, gatewayAddress);
    synchronized(outgoingQueue) {
      this.outgoingQueue.addLast(frame);
    }
  }

  private void forward() {
    IFrame frame = dequeueOutgoing();
    if (frame == null)
      return;
    wlanLink.transmit(frame, this);
  }

  public void run() {
    while (this.up) {
      forward();
      process();
    }
  }

  @Override
  public void setThread(Thread t) {
    if (thread == null) {
      thread = t;
    }
  }

  @Override
  public void setDevice(IDevice dev) {
    if(device == null) {
      device = dev;
    }
  }

  @Override
  public void setLink(ILink<? extends IHardwareInterface> link) {
    if (wlanLink == null) {
      wlanLink = (WirelessLink) link;
      gatewayAddress = wlanLink.getGatewayAddress();
    }
  }

  @Override
  public boolean isUp() {
    return up;
  }

  @Override
  public ArrayList<IHardwareInterface> getConnectedHosts() {
    ArrayList<IHardwareInterface> result = new ArrayList<>();
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

  @Override
  public double getCoverageDistance() {
    return DOUBLE_COVERAGE_DISTANCE;
  }

  @Override
  public int getMaxHosts() {
    return INT_MAX_HOSTS;
  }

  @Override
  public IDevice getDevice() {
    return device;
  }

  @Override
  public void setMacProtocol(IMacProtocol mac) {
    if (macProtocol == null) {
      macProtocol = mac;
    }
  }

  @Override
  public ILink<? extends IHardwareInterface> getLink() {
    return wlanLink;
  }

  @Override
  public void receive(IFrame message) {
    enqueueIncoming(message);
  }

  private IFrame dequeueIncoming() {
    IFrame msg = null;
    synchronized(incomingQueue) {
      try {
        msg = incomingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
    return msg;
  }

  private IFrame dequeueOutgoing() {
    IFrame msg = null;
    synchronized(outgoingQueue) {
      try {
        msg = outgoingQueue.getFirst();
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
    return msg;
  }

  private void enqueueIncoming(IFrame msg) {
    synchronized(outgoingQueue) {
      try {
        outgoingQueue.addLast(msg);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
  }

  private void enqueueOutgoing(IFrame msg) {
    synchronized(outgoingQueue) {
      try {
        outgoingQueue.addLast(msg);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
  }


}
