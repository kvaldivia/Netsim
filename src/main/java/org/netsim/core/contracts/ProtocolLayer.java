package org.netsim.core.contracts;

public interface ProtocolLayer extends StackStep {
    Pdu buildPdu(String srcIp, String destIp, Pdu message);
    PhysicalInterface tableLookup(String destIp);
}
