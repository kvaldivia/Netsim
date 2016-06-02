package org.netsim.core.contracts;

public interface Link {
    public void transport(Interface src, Interface dest, String msg);
}
