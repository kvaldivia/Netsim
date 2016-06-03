package org.netsim.core.models;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.Pdu;

public class WlanInterface implements Interface {
    private String id;
    private String ipAddress;
    private StackStep port;
    private StackStep upperStep;
    private StackStep lowerStep;

    public WlanInterface(String id, String ip, StackStep upper, StackStep lower) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.upperStep = upper;
        this.lowerStep = lower;
    }

    public String getId() {
        return this.id;
    }

    public String getAddress() {
        return this.ipAddress;
    }

    public StackStep getPort() {
        return this.port;
    }

    public void setPort(StackStep port) {
        this.port = port;
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
