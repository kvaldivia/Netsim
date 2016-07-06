package org.netsim.networking.hardware;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.inject.Inject;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.protocol.EthernetFrame;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IMacFrame;
import org.netsim.networking.protocol.IMacProtocol;

public class EthernetInterface extends IHardwareInterface {
  private String address;
  private IDevice device;
  private boolean up;
  private WiredLink wiredLink;
  private Thread thread;
  private IMacProtocol macProtocol;

  private LinkedList<IFrame> outgoingQueue;
  private LinkedList<IFrame> incomingQueue;

  public final double DOUBLE_COVERAGE_DISTANCE = 0;
  public final int INT_MAX_HOSTS = 2;

  @Inject
  public EthernetInterface() {
    address = MacAddressGenerator.getInstance().newMacAddress();
    macProtocol = null;
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

  @Override
  public void setDevice(IDevice dev) {
    if (device == null) {
      device = dev;
    }
  }

  @Override
  public ArrayList<IHardwareInterface> getConnectedHosts() {
    ArrayList<IHardwareInterface> result = new ArrayList<>();

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
  public ILink<? extends IHardwareInterface> getLink() {
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

  @Override
  public void send(IMacFrame payload, String dest, String source) {
    IFrame msg = new EthernetFrame(payload, dest, source);
    enqueueOutgoing(msg);
  }

  @Override
  public void send(IMacFrame payload, String dest) {
    IFrame msg = new EthernetFrame(payload, dest, this.address);
    enqueueOutgoing(msg);
  }

  @Override
  public IDevice getDevice() {
    // TODO Auto-generated method stub
    return null;
  }

  private void process() {
    IFrame msg = dequeueIncoming();
    if (msg == null) 
      return;

    if (msg.getSourceAddress() == this.address) {
      macProtocol.receive(msg.getPayload(), this.address);
    } else {
      macProtocol.receive(msg);
    }
  }

  private void forward() {
    IFrame msg = dequeueOutgoing();
    if (msg == null)
      return;

    wiredLink.transmit(msg, this);
  }

  @Override
  public void run() {
    while (this.up) {
      process();
      forward();
    }
  }

  @Override
  public void setMacProtocol(IMacProtocol mac) {
    if (mac == null)
      macProtocol = mac;
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

  

  @Override
  public void receive(IFrame message) {
    enqueueIncoming((IFrame) message);
  }

  @Override
  public void setLink(ILink<? extends IHardwareInterface> link) {
    // TODO Auto-generated method stub
    if (wiredLink == null) {
      wiredLink = (WiredLink) link;
    }
  }

 }
