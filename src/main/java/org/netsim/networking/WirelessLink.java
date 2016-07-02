package org.netsim.networking;

import java.util.ArrayList;

public class WirelessLink implements ILink<WlanInterface> {
  ArrayList<WlanInterface> hosts;
  WlanInterface gateway;


  public WirelessLink(WlanInterface gway) {
    hosts = new ArrayList<>();
    gateway = gway;
    hosts.add(gway);
  }

  @Override
  public void connect(WlanInterface host) {
    hosts.add(host);
    host.setLink(this);
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

}
