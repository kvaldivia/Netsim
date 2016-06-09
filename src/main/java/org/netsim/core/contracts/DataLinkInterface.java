package org.netsim.core.contracts;

public interface DataLinkInterface extends LayerInterface {
    void linkWith(DataLinkInterface remote);
    Link getLink();
    void unlink();
    void sendPayload(Pdu payload);
    void receivePayload(Pdu payload);
    void sendPdu(Pdu pdu);
    void receivePdu(Pdu pdu);
}
