package org.netsim.networking;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.netsim.networking.device.IDevice;
import org.netsim.networking.hardware.IHardwareInterface;

public class AccessPointTest {
  private IDevice ap;
  private NetworkingComponent networkingComponent;
  private ArrayList<IHardwareInterface> interfaces;

  @Before
  public void initialize() {

  }

  @Test
  public void anAccessPointsHasOneWlanInterface() {

  }

  @Test
  public void forwardsReceivedFramesThroughAllInterfaces() {
    
  }
  
}
