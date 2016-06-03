package org.netsim.core.models;

import org.netsim.core.contracts.Port;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.Pdu;
import org.netsim.core.contracts.Link;

public class EthernetPort implements Port {
    private String address;
    private Link link;
    private StackStep ethInterface;
    private StackStep upperStep;
    private StackStep lowerStep;

    public EthernetPort(String address, StackStep upper, StackStep lower) {
        this.address = address;
        this.upperStep = upper;
        this.lowerStep = lower;
    }

    public String getAddress() {
        return this.address;
    }

    public StackStep getInterface() {
        return this.ethInterface;
    }

    public Link getLink() {
        return this.link;
    }

    public void linkWith(Port remote) {
        // TODO: port linking.
    }

    public void unlink() {
        this.link.remove();
    }

    public void receivePayload(Pdu payload, StackStep previousStep) {
        sendPayload(payload);
    }
    
    private void sendPayload(Pdu payload) {
        this.upperStep.receivePayload(payload, this);
    }

    public void receivePdu(Pdu pdu, StackStep previousStep) {
        sendPdu(pdu);
    }

    private void sendPdu(Pdu pdu) {
        this.lowerStep.receivePdu(pdu, this);
    }
}
