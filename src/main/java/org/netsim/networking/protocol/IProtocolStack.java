package org.netsim.networking.protocol;

import org.netsim.networking.hardware.IHardwareInterface;

public interface IProtocolStack {

	void updateInterfaces();

	void addInterface(IHardwareInterface eth);

}
