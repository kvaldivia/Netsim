package org.netsim.networking.protocol;

import java.util.ArrayList;

import org.netsim.networking.hardware.AHardwareInterface;

public interface ILink <HW extends AHardwareInterface<?extends IFrame>>{

  void connect(HW gway);

  <D extends IFrame>
  void transmit(D frame, HW src);

  ArrayList<HW> getHosts();

  boolean isLinkFull();
}
