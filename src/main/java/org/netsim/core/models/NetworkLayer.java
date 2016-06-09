package org.netsim.core.models;

import java.util.ArrayList;

import org.netsim.core.contracts.PhysicalInterface;
import org.netsim.core.contracts.PhysicalInterface;
import org.netsim.core.contracts.ProtocolLayer;
import org.netsim.core.contracts.Pdu;
import org.netsim.core.contracts.AbstractNode;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.Builder;
import org.netsim.core.models.Packet;

public class NetworkLayer implements ProtocolLayer {
    private AbstractNode node;
    private StackStep upperStep;
    private StackStep lowerStep;
    private ArrayList<PhysicalInterface> interfaces;

    private NetworkLayer(NetworkLayerBuilder builder) {
        this.node = node;
        this.upperStep = builder.upperStep;
        this.lowerStep = builder.lowerStep;
        this.interfaces = builder.interfaces;
    }

    public AbstractNode getNode() {
        return this.node;
    }

    public ArrayList<PhysicalInterface> listInterfaces() {
        return this.interfaces;
    }

    public PhysicalInterface tableLookup(String ip)
    {
        // TODO: lookup logic
        return this.interfaces.get(0);
    }

    public Pdu buildPdu(String src, String dest, Pdu message)
    {
        return new Packet.PacketBuilder(message, src, dest).build();
    }

    public void receivePdu(Pdu pdu)
    {
        return;
    }

    public void receivePayload(Pdu payload)
    {
        return;
    }

    public static class NetworkLayerBuilder implements Builder{
        private final StackStep upperStep;
        private final StackStep lowerStep;
        private final ArrayList<PhysicalInterface> interfaces;

        public NetworkLayerBuilder(StackStep upperStep
            , StackStep lowerStep, AbstractNode node) 
        {
           this.upperStep = upperStep;
           this.lowerStep = lowerStep;
           this.interfaces = new ArrayList<PhysicalInterface>();
        }

        public NetworkLayer build()
        {
            return new NetworkLayer(this);
        }

        private void subscribeToInterfaces(ArrayList<PhysicalInterface> interfaces)
        {
            for(PhysicalInterface current : interfaces) 
            {
                //TODO: Subject interface is not implemented yet
                this.interfaces.add(current);
            }
        }
    }

}
