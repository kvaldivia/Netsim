package org.netsim.core;

public class WlanFrame extends Frame {
    private String destinationMac;
    private String sourceMac;
    private LlcFrame payload;
    private String frameCheckSequence;

    public WlanFrame(String srcMac, String destMac, LlcFrame payload) {
        this.sourceMac = srcMac;
        this.destinationMac = destMac;
        this.payload = payload;
    }
    
    public String getSourceAddress() {
        return this.sourceMac;
    }

    public String getDestinationAddress() {
        return this.destinationMac;
    }

    public LlcFrame getPayload() {
        return this.payload;
    }
    
    public String getFCS() {
        return this.frameCheckSequence;
    }
}
