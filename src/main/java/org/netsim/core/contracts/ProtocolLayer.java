package org.netsim.core.contracts;

public interface ProtocolLayer extends StackStep {
    void receivePdu(Pdu pdu, StackStep previousStep);
    void receivePayload(Pdu payload, StackStep previousStep);
    Pdu buildPdu(String srcIp, String destIp, String message);
    Interface tableLookup(String destIp);
}
