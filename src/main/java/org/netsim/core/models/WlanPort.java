package org.netsim.core.models;

import org.netsim.core.contracts.Port;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.Pdu;
import org.netsim.core.contracts.Link;

public class WlanPort implements Port {
    private String address;
    private StackStep wlanInterface;
    private Link link;
    private int coverageDistance;
    private StackStep lowerStep;
    private StackStep upperStep;

    public WlanPort(String mac, StackStep lower, StackStep upper) {
        this.address = mac;
        this.lowerStep = lower;
        this.upperStep = upper;
        this.coverageDistance = 100;
    }

    public String getAddress() {
        return this.address;
    }

    public StackStep getInterface() {
        return this.wlanInterface;
    }

    public void linkWith(Port remote) {
        // TODO: Port linking
    }

    public Link getLink() {
        return this.link;
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
