package org.netsim.networking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IMacFrame;
import org.netsim.networking.protocol.MacFrame;
import org.netsim.networking.protocol.WlanFrame;

public class WlanInterfaceTest {
  private IMacFrame packet;
  private WlanInterface wlanInterface;
  private String srcAddress;
  private String destAddress;
  private String consumerId;
  private WlanFrame frame;
  private String gateway;

  @Before
  public void initialize() {
    wlanInterface = new WlanInterface();
    srcAddress = wlanInterface.getAddress();
    destAddress = "BB:BB:BB:BB:BB:BB";
    consumerId = "IP";
    gateway = null;
  }

  @Test
  public void shouldCreateValidWlanFrame() {
  }

  @Test
  public void shouldCorrectlyAddConsumer() {
  }

  @Test
  public void coverageDistanceShouldBeGreaterThanZero() {
  }
}
