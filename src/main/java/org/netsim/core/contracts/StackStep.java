package org.netsim.core.contracts;

public interface StackStep {
    void receivePayload(Pdu payload, StackStep previousStep);
    void receivePdu(Pdu pdu, StackStep previousStep);
}
