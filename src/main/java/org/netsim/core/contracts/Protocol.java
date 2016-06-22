package org.netsim.core;

public interface Protocol<T1,T2> {
    public T2 wrap(T1 payload, String destAddress);
    public T1 unwrap(T2 pdu);
}
