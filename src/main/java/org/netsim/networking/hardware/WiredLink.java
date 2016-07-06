package org.netsim.networking.hardware;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.commons.collections4.list.FixedSizeList;
import org.netsim.networking.protocol.EthernetFrame;
import org.netsim.networking.protocol.IFrame;

public class WiredLink implements ILink<EthernetInterface> {
  private FixedSizeList<EthernetInterface> hosts;

  @Inject
  public WiredLink() {
    ArrayList<EthernetInterface> hostsList = new ArrayList<>(2);
    hosts = FixedSizeList.fixedSizeList(hostsList);
  }
  
  public void connect(EthernetInterface host) {
    try {
      hosts.add(host);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  @Override
  public void transmit(IFrame frame, EthernetInterface src) {
    for (EthernetInterface host: hosts) {
      if (host == src) {
        continue;
      } else {
        host.receive((EthernetFrame)frame);
      }
      
    }
  }

  @Override
  public ArrayList<EthernetInterface> getHosts() {
    ArrayList<EthernetInterface> result = new ArrayList<>();
    for (EthernetInterface eth: hosts) {
      result.add(eth);
    }
    return result;
  }

  @Override
  public boolean isLinkFull() {
    return this.hosts.isFull();
  }

  @Override
  public String getGatewayAddress() {
    return null;
  }

}
