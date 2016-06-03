package org.netsim.core.models;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.Pdu;

public class EthernetInterface implements Interface {
    private String id;
    private String address;
    private StackStep port;
    private StackStep upperStep;
    private StackStep lowerStep;

    public EthernetInterface(String id, String ip, StackStep upper, StackStep lower) {
        this.id = id;
        this.address = ip;
        this.upperStep = upper;
        this.lowerStep = lower;
    }

    public String getId() {
        return this.id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setPort(StackStep port) {
        this.port = port;
    }

    public StackStep getPort() {
        return this.port;
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
