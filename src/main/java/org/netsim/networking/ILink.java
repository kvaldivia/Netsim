package org.netsim.networking;

public interface ILink <HW extends AHardwareInterface<?extends IPacket,?extends IFrame>>{

  void connect(HW gway);

  <D extends IFrame>
  void transmit(D frame, HW src);
}
