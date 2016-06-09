package org.netsim.core.contracts;

public interface LayerInterface 
{
    public String getAddress();
    public void receivePayload(Pdu payload);
    public void receivePdu(Pdu pdu);
    public void sendPayload(Pdu payload);
    public void sendPdu(Pdu pdu);
}
