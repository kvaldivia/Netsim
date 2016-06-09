package org.netsim.core.models;

import org.netsim.core.contracts.DataLinkInterface;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.Pdu;
import org.netsim.core.contracts.Link;

public class WlanPort implements DataLinkInterface 
{
    private String address;
    private Link link;
    private int coverageDistance;
    private StackStep lowerStep;
    private StackStep upperStep;

    public WlanPort(String mac, int coverage, StackStep lower, StackStep upper) 
    {
        this.address = mac;
        this.lowerStep = lower;
        this.upperStep = upper;
        this.coverageDistance = 100;
    }

    public String getAddress() 
    {
        return this.address;
    }

    public int getCoverageDistance()
    {
        return this.coverageDistance;
    }

    public void linkWith(DataLinkInterface remote) 
    {
        // TODO: Port linking
    }

    public Link getLink() 
    {
        return this.link;
    }

    public void unlink() 
    {
        this.link.remove();
    }

    public void receivePayload(Pdu payload) 
    {
        sendPayload(payload);
    }

    public void sendPayload(Pdu payload) 
    {
        this.upperStep.receivePayload(payload);
    }

    public void receivePdu(Pdu pdu) 
    {
        sendPdu(pdu);
    }

    public void sendPdu(Pdu pdu) 
    {
        this.lowerStep.receivePdu(pdu);
    }
}
