package org.netsim.networking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NetworkTest {
  private Network nw;
  private NetworkingComponent netComponent;

  @Before
  public void initialize() {
    netComponent = DaggerNetworkingComponent.builder()
      .networkModule(new NetworkModule()).build();
    nw = netComponent.provideNetwork();

    netComponent = DaggerNetworkingComponent.builder()
      .deviceModule(new DeviceModule()).build();
  }

  @Test
  public void shouldCorrectlyAddAccessPoint() {
    ADevice<? extends IFrame> ap = netComponent.provideAccessPoint();
    nw.addAccessPoint(ap);
    assertTrue(nw.listNodes().contains(ap));
  }

  @Test
  public void shouldCorrectlyAddMobileNode() {
    
  }

}