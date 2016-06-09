package org.netsim.core.contracts;

public interface Link {
    void transport(DataLinkInterface dest, Pdu msg);
    void remove();
}
