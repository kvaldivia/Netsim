package org.netsim.core.contracts;

public interface NetworkInterface extends LayerInterface {
    void receivePayload(Pdu payload, StackStep step);
    void receivePdu(Pdu pdu, StackStep previousStep);
    void sendPdu(Pdu pdu);
    void sendPayload(Pdu pdu);
}
