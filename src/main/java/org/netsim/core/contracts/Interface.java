package org.netsim.core;

public abstract class Interface<T1,T2> implements Protocol<T1,T2> {
    private String macAddress;

    public abstract String getAddress();
    public abstract void receive(T2 frame);
    public abstract void send(T1 frame, String destAddress);
    public abstract T2 wrap(T1 payload, String destAddress);
    public abstract T1 unwrap(T2 frame);
}
