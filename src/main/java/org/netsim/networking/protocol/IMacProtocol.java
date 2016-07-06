package org.netsim.networking.protocol;

import java.util.List;

import org.netsim.networking.hardware.IHardwareInterface;

public interface IMacProtocol extends IProtocol<IPacket, IMacFrame> {

	void addInterface(IHardwareInterface iface);

  void addInterfaces(List<IHardwareInterface> ifaces);

  void send(IPacket payload, String dest, String protocol, String through);

  void receive(IMacFrame msg, String through);

  void receive(IFrame msg);

  void send(IFrame frame);

}
