package org.netsim.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.Before;

public class EthernetInterfaceTest{
    private LlcFrame llcFrame;
    private EthernetInterface ethInterface;
    private String srcAddress;
    private String destAddress;
   
    @Before
    public void initialize() {
        llcFrame = new LlcFrame();
        ethInterface = new EthernetInterface();
        srcAddress = ethInterface.getAddress();
        destAddress = "AA:AA:AA:AA:AA:AA";
    }
    @Test
    public void shouldCreateValidEthernetFrame() {
       
        EthernetFrame actualFrame = ethInterface.wrap(llcFrame, destAddress);

        assertEquals(actualFrame.getSourceAddress(), srcAddress);
        assertEquals(actualFrame.getDestinationAddress(), destAddress);
        assertSame(actualFrame.getPayload(), llcFrame);

    }

    @Test
    public void shouldUnwrapEthernetFrame() {
        EthernetFrame ethFrame = ethInterface.wrap(llcFrame, destAddress);
        LlcFrame payload = ethInterface.unwrap(ethFrame);

        assertEquals(llcFrame, payload);
    }

}
