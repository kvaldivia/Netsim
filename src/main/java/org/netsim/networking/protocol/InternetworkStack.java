package org.netsim.networking.protocol;

import javax.inject.Inject;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.hardware.IHardwareInterface;

public class InternetworkStack implements IProtocolStack {
  private IProtocol<? extends IDataUnit, ? extends IDataUnit> protocols;
  private IDevice device;

  @Inject
  public InternetworkStack(IMacProtocol mac, INetworkProtocol net) {

  }

  @Override
  public void updateInterfaces() {
    // TODO Auto-generated method stub

  }

  @Override
  public void addInterface(IHardwareInterface eth) {
    // TODO Auto-generated method stub

  }

}
