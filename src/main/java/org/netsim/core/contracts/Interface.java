package org.netsim.core.contracts;


public interface Interface {
    public String getIpAddress();
    public String getMacAddress();
    public Node getNode();
    public Link getLink();
    public void linkWith(Interface remote);
    public void unlink();
}
