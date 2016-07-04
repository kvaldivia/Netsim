package org.netsim.networking.hardware;

import java.util.ArrayList;

import org.netsim.networking.protocol.IFrame;

public interface ILink <HW extends IHardwareInterface<?extends IFrame>>{

  void connect(HW gway);

  <D extends IFrame>
  void transmit(D frame, HW src);

  ArrayList<HW> getHosts();

  boolean isLinkFull();
}
