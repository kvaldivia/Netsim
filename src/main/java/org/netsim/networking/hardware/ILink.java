package org.netsim.networking.hardware;

import java.util.ArrayList;

import org.netsim.networking.protocol.IFrame;

public interface ILink <HW extends IHardwareInterface>{

  void connect(HW gway);

  void transmit(IFrame frame, HW src);

  ArrayList<HW> getHosts();

  boolean isLinkFull();

  String getGatewayAddress();
}
