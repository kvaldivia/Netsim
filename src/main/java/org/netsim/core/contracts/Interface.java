package org.netsim.core.contracts;

public interface Interface extends StackStep {
    String getId();
    String getAddress();
    StackStep getPort();
    void receivePayload(Pdu payload, StackStep previousStep);
    void receivePdu(Pdu pdu, StackStep previousStep);
}
