package org.netsim.core.models;

import org.netsim.core.contracts.PhysicalInterface;
import org.netsim.core.contracts.ProtocolLayer;
import org.netsim.core.contracts.Pdu;
import org.netsim.core.contracts.AbstractNode;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.DataLinkInterface;
import org.netsim.core.contracts.Builder;

import java.util.ArrayList;


public class DataLinkLayer implements ProtocolLayer {
    private StackStep lowerStep;
    private StackStep upperStep;
    private ArrayList<PhysicalInterface> interfaces;

    private DataLinkLayer(DataLinkLayerBuilder builder)
    {
        this.lowerStep = builder.lowerStep;
        this.upperStep = builder.upperStep;
        this.interfaces = builder.interfaces;
    }
    
    public Pdu buildPdu(String src, String dest, Pdu message)
    {
        return new Frame.FrameBuilder(message, src, dest).build();
    }

    public void receivePdu(Pdu pdu)
    {
        sendPdu(pdu);
    }

    public void sendPdu(Pdu pdu)
    {
        this.lowerStep.receivePdu(pdu);
    }

    public void receivePayload(Pdu payload)
    {
        sendPayload(payload);
    }

    public void sendPayload(Pdu payload)
    {
        this.upperStep.receivePayload(payload);
    }

    public PhysicalInterface tableLookup(String destIp)
    {
        return this.interfaces.get(0);
    }

    public static class DataLinkLayerBuilder implements Builder
    {
        private StackStep lowerStep;
        private StackStep upperStep;
        private ArrayList<PhysicalInterface> interfaces;

        public DataLinkLayerBuilder(StackStep upperStep, StackStep lowerStep)
        {
        }

        public DataLinkLayer build()
        {

        }
    }

}
