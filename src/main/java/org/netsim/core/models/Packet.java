package org.netsim.core.models;

import org.netsim.core.contracts.Pdu;
import org.netsim.core.contracts.Builder;

public class Packet implements Pdu 
{
    private Pdu payload;
    private String sourceAddress;
    private String destAddress;

    private Packet(PacketBuilder builder)
    {
        this.payload = builder.payload;
        this.sourceAddress = builder.sourceAddress;
        this.destAddress= builder.destAddress;
    }

    public String getSourceAddress()
    {
        return this.sourceAddress;
    }

    public String getDestAddress()
    {
        return this.destAddress;
    }

    public Pdu getPayload()
    {
        return this.payload;
    }
    
    public static class PacketBuilder implements Builder 
    {
        private final Pdu payload;
        private final String sourceAddress;
        private final String destAddress;

        public PacketBuilder(Pdu payload, String source, String dest) 
        {
            this.payload = payload;
            this.sourceAddress = source;
            this.destAddress = dest;
        }

        public Packet build() 
        {
            return new Packet(this);
        }
    }
}
