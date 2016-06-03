package org.netsim.core.contracts;

public interface Port extends StackStep{
    String getAddress();
    StackStep getInterface();
    void linkWith(Port port);
    Link getLink();
    void unlink();
    void receivePayload(Pdu payload, StackStep previousStep);
    void receivePdu(Pdu pdu, StackStep previousStep);
}
