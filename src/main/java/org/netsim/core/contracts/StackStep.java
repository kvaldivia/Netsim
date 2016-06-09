package org.netsim.core.contracts;

public interface StackStep {
    void receivePayload(Pdu payload);
    void receivePdu(Pdu pdu);
}
