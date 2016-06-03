package org.netsim.core.contracts;

public interface Link {
    void transport(Port dest, Pdu msg);
    void remove();
}
