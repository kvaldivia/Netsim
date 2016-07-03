package org.netsim.networking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

public class EthernetInterfaceTest{
    private IPacket packet;
    private EthernetInterface ethInterface;
    private String srcAddress;
    private String destAddress;
    private String consumerId;
    private EthernetFrame frame;
   
    @Before
    public void initialize() {
      packet = new IpPacket();
      ethInterface = new EthernetInterface();
      srcAddress = ethInterface.getAddress();
      destAddress = "AA:AA:AA:AA:AA:AA";
      consumerId = "IP";

      frame = new EthernetFrame(packet, srcAddress, destAddress, consumerId);
    }
    @Test
    public void shouldCreateValidEthernetFrame() {
        EthernetFrame actualFrame = ethInterface.wrap(packet, destAddress, consumerId);

        assertEquals(actualFrame.getSourceAddress(), srcAddress);
        assertEquals(actualFrame.getDestinationAddress(), destAddress);
        assertSame(actualFrame.getPayload(), packet);
    }

    @Test
    public void shouldCorrectlyAddConsumer() {
        Internet validConsumer = new Internet();
        this.ethInterface.addConsumer(validConsumer);
        assertTrue(ethInterface.listConsumers().contains(validConsumer));
    }
}
