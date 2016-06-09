package org.netsim.core.models;

import org.netsim.core.contracts.PhysicalInterface;
import org.netsim.core.contracts.NetworkInterface;
import org.netsim.core.contracts.DataLinkInterface;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.Pdu;

public class WlanInterface extends PhysicalInterface {
    private String id;
    private NetworkInterface net;
    private DataLinkInterface port;
    private StackStep upperStep;
    private StackStep lowerStep;

    public WlanInterface(String id, StackStep upper, StackStep lower) 
    {
        this.id = id;
        this.upperStep = upper;
        this.lowerStep = lower;
    }

    public String getId() 
    {
        return this.id;
    }

    public String getIp() 
    {
        return this.net.getAddress();
    }

    public String getMac() 
    {
        return this.port.getAddress();
    }

    public void receivePayload(Pdu payload) 
    {
        sendPayload(payload);
    }

    private void sendPayload(Pdu payload) 
    {
        this.upperStep.receivePayload(payload);
    }


    public void receivePdu(Pdu pdu) 
    {
        sendPdu(pdu);
    }

    private void sendPdu(Pdu pdu) 
    {
        this.lowerStep.receivePdu(pdu);
    }
}
