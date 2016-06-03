package org.netsim.core.contracts;

public interface Pdu {
    <T> T getPayload();
    String getSourceAddress();
    String getDestAddress();
}
