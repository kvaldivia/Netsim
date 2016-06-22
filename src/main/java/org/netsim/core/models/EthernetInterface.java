package org.netsim.core;

public class EthernetInterface extends Interface<LlcFrame,EthernetFrame> {
    private String macAddress;

    public EthernetInterface() {
        this.macAddress = MacAddressGenerator.getInstance().newMacAddress();
    }

    public String getAddress() {
        return this.macAddress;
    }

    public void receive(EthernetFrame frame) {
        
    }

    public void send(LlcFrame packet, String destAddress) {

    }

    public EthernetFrame wrap(LlcFrame payload, String destAddress) {
        String srcAddress = this.macAddress;
        EthernetFrame ethFrame = 
            new EthernetFrame(srcAddress, destAddress, payload);

        return ethFrame;
    }

    public LlcFrame unwrap(EthernetFrame frame) {
        return frame.getPayload();
    }

}
