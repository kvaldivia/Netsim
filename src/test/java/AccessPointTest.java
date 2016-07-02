package org.netsim.device;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.netsim.device.AccessPoint;
import org.netsim.device.DeviceComponent;
import org.netsim.device.DeviceModule;
import org.netsim.networking.AHardwareInterface;
import org.netsim.networking.IFrame;
import org.netsim.networking.IPacket;


public class AccessPointTest {
  private AccessPoint ap;
  private DeviceComponent deviceComponent;
  private ArrayList<AHardwareInterface<? extends IPacket, ? extends IFrame>> interfaces;

  @Before
  public void initialize() {
    deviceComponent = DaggerDeviceComponent.builder().deviceModule(new DeviceModule()).build();
    ap = deviceComponent.provideAccessPoint();
    interfaces = ap.listInterfaces();
  }

  @Test
  public void forwardsReceivedFramesThroughAllInterfaces() {
    
  }
  
}
