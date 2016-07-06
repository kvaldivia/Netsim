package org.netsim.networking.protocol;

import java.util.HashMap;

import javax.inject.Inject;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.hardware.IHardwareInterface;

public class SecondLayerStack implements IProtocolStack {
  private HashMap<String,IProtocol<? extends IDataUnit, ? extends IDataUnit>> protocols;
  private IDevice device;


  @Inject
  public SecondLayerStack() {
    protocols = new HashMap<>();
  }

  @Override
  public void updateInterfaces() {
  }

  @Override
  public void addInterface(IHardwareInterface eth) {
    // TODO Auto-generated method stub

  }
}
