package org.netsim.core;

public abstract class Frame {
    private String destinationMac;
    private String sourceMac;
    private LlcFrame payload;
    private String frameCheckSequence;
    
    public abstract String getSourceAddress();
    public abstract String getDestinationAddress();
    public abstract LlcFrame getPayload();
    public abstract String getFCS();
}
