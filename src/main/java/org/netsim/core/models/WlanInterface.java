package org.netsim.core;

public class WlanInterface extends Interface<LlcFrame,WlanFrame> {
    private String macAddress;

    public WlanInterface() {
        this.macAddress = MacAddressGenerator.getInstance().newMacAddress();
    }

    public String getAddress() {
        return this.macAddress;
    }
    public void receive(WlanFrame frame) {

    }
    
    public void send(LlcFrame frame, String destAddress) {

    }

    public WlanFrame wrap(LlcFrame payload, String destAddress) {
        return new WlanFrame(macAddress, destAddress, payload);
    }
    public LlcFrame unwrap(WlanFrame frame) {
        return frame.getPayload();
    }

}
