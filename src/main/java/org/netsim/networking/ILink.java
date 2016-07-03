package org.netsim.networking;

import java.util.ArrayList;

public interface ILink <HW extends AHardwareInterface<?extends IFrame>>{

  void connect(HW gway);

  <D extends IFrame>
  void transmit(D frame, HW src);

  ArrayList<HW> getHosts();

  boolean isLinkFull();
}
