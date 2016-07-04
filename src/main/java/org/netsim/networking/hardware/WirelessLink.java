package org.netsim.networking.hardware;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.commons.collections4.list.FixedSizeList;
import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.WlanFrame;

public class WirelessLink implements ILink<WlanInterface> {
  FixedSizeList<WlanInterface> hosts;
  WlanInterface gateway;

  @Inject
  public WirelessLink(WlanInterface gway) {
    ArrayList<WlanInterface> hostsList = new ArrayList<WlanInterface>(gway.INT_MAX_HOSTS);
    hosts = FixedSizeList.fixedSizeList(hostsList);
    gateway = gway;
    hosts.add(gway);
  }

  @Override
  public void connect(WlanInterface host) {
    try {
      hosts.add(host);
      host.setLink(this);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  @Override
  public <D extends IFrame> 
  void transmit(D frame, WlanInterface src) {
    for (WlanInterface host: hosts) {
      if (host == src) {
        continue;
      } else {
        host.receive((WlanFrame) frame, null);
      }
    }
  }

  @Override
  public ArrayList<WlanInterface> getHosts() {
    ArrayList<WlanInterface> result = new ArrayList<>();
    for (WlanInterface wlan: hosts) {
      result.add(wlan);
    }
    return result;
  }

  @Override
  public boolean isLinkFull() {
    return hosts.isFull();
  }

}
