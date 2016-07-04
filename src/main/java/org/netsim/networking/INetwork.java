package org.netsim.networking;

import org.netsim.networking.device.ADevice;
import org.netsim.networking.device.AccessPoint;
import org.netsim.networking.protocol.IFrame;

public interface INetwork {

	void addAccessPoint(ADevice<? extends IFrame> ap);

	void addMobileNode(AccessPoint mob);

}
