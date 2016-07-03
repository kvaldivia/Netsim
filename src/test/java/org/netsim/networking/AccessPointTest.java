package org.netsim.networking;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AccessPointTest {
  private AccessPoint ap;
  private NetworkingComponent networkingComponent;
  private ArrayList<AHardwareInterface<? extends IFrame>> interfaces;

  @Before
  public void initialize() {
    networkingComponent = DaggerNetworkingComponent.builder()
      .deviceModule(new DeviceModule()).build();
    ap = networkingComponent.provideAccessPoint();
    interfaces = ap.listInterfaces();

    for (AHardwareInterface<? extends IFrame> wlan: interfaces) {
      if (wlan.isUp())
       break; 
    }
  }

  @Test
  public void forwardsReceivedFramesThroughAllInterfaces() {
    
  }
  
}
