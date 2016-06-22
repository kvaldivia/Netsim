package org.netsim.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.Before;

public class WlanInterfaceTest {
    private LlcFrame llcFrame;
    private WlanInterface wlanInterface;
    private String srcAddress;
    private String destAddress;

    @Before
    public void initialize() {
        llcFrame = new LlcFrame();
        wlanInterface = new WlanInterface();
        srcAddress = wlanInterface.getAddress();
        destAddress = "BB:BB:BB:BB:BB:BB";
    }

    @Test
    public void shouldCreateValidWlanFrame() {
        WlanFrame actualWlanFrame = wlanInterface.wrap(llcFrame, destAddress);
        
        assertEquals(srcAddress, actualWlanFrame.getSourceAddress());
        assertEquals(destAddress, actualWlanFrame.getDestinationAddress());
        assertEquals(llcFrame, actualWlanFrame.getPayload());
    }

    @Test 
    public void shouldUnwrapPayload() {
        WlanFrame actualWlanFrame = wlanInterface.wrap(llcFrame, destAddress);
        LlcFrame payload = wlanInterface.unwrap(actualWlanFrame);

        assertEquals(llcFrame, payload);
    }
}
