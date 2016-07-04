package org.netsim.networking;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.netsim.networking.device.IDevice;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.protocol.IFrame;

public class AccessPointTest {
  private IDevice<? extends IFrame> ap;
  private NetworkingComponent networkingComponent;
  private ArrayList<IHardwareInterface<? extends IFrame>> interfaces;

  @Before
  public void initialize() {
    networkingComponent = DaggerNetworkingComponent.builder()
      .deviceModule(new DeviceModule()).build();
    ap = networkingComponent.provideAccessPoint();
    interfaces = ap.listInterfaces();
  }

  @Test
  public void anAccessPointsHasOneWlanInterface() {
    int count = ap.listWirelessInterfaces().size();
    assertTrue(count == 1);
  }

  @Test
  public void forwardsReceivedFramesThroughAllInterfaces() {
    
  }
  
}
