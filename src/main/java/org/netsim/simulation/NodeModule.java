package org.netsim.simulation;

import org.netsim.networking.device.AccessPoint;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.simulation.node.INode;
import org.netsim.simulation.node.WalkingNode;

import dagger.Module;
import dagger.Provides;

@Module
public class NodeModule {

  @Provides
  public INode provideStandingNode(AccessPoint ap, EthernetInterface eth, WlanInterface wlan) {
    ap.addEthernetInterface(eth);
    ap.addWlanInterface(wlan);
    return new WalkingNode(ap);
  }
}
