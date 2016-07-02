package org.netsim.networking;

import java.util.ArrayList;

public class WiredLink implements ILink<EthernetInterface> {
  private ArrayList<EthernetInterface> hosts;

  public WiredLink() {
    hosts = new ArrayList<>(2);
  }
  
  public void connect(EthernetInterface host) {
    try {
      hosts.add(host);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  @Override
  public <D extends IFrame> 
  void transmit(D frame, EthernetInterface src) {
    for (EthernetInterface host: hosts) {
      if (host == src) {
        continue;
      } else {
        host.receive((EthernetFrame) frame, null);
      }
      
    }
  }

}
