package org.netsim.networking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IPacket;
import org.netsim.networking.protocol.Internet;
import org.netsim.networking.protocol.IpPacket;
import org.netsim.networking.protocol.WlanFrame;

public class WlanInterfaceTest {
    private IPacket packet;
    private WlanInterface wlanInterface;
    private String srcAddress;
    private String destAddress;
    private String consumerId;
    private WlanFrame frame;
    private String gateway;

    @Before
    public void initialize() {
      packet = new IpPacket();
      wlanInterface = new WlanInterface();
      srcAddress = wlanInterface.getAddress();
      destAddress = "BB:BB:BB:BB:BB:BB";
      consumerId = "IP";
      gateway = null;
      frame = new WlanFrame(packet, srcAddress, destAddress, gateway, consumerId);
    }

    @Test
    public void shouldCreateValidWlanFrame() {
        WlanFrame actualWlanFrame = 
          wlanInterface.wrap(packet, srcAddress, destAddress, consumerId);
        
        assertEquals(srcAddress, actualWlanFrame.getSourceAddress());
        assertEquals(destAddress, actualWlanFrame.getDestinationAddress());
        assertEquals(packet, actualWlanFrame.getPayload());
    }

    @Test
    public void shouldCorrectlyAddConsumer() {
        Internet validConsumer = new Internet();
        this.wlanInterface.addConsumer(validConsumer);
        assertTrue(wlanInterface.listConsumers().contains(validConsumer));
    }
}
