package org.netsim.core.models;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.Node;
import org.netsim.core.contracts.Link;
import org.netsim.core.lib.LinkFactory;

public class WirelessInterface implements Interface {
    private Node node;
    private double coverageDistance;
    private String ipAddress;
    private String macAddress;
    private LinkFactory linkFactory;
    private Link link;
    private int numConnections;

    public WirelessInterface(Node node, String ipAdd,  int numConnections) {
        this.ipAddress = ipAdd;
        this.macAddress = "FF:FF:FF:FF";
        this.numConnections = numConnections;
        this.linkFactory = LinkFactory.getReference();
        this.link = this.linkFactory.createWireless(this);
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public Link getLink() {
        return this.link;
    }

    public void createLink() {
        this.linkFactory.createWireles(this);
    }

    public void linkWith(Interface remote) {
        this.link.addInterface(remote);
    }

    public void unlink(Interface remote) {
        this.link.remove(remote);
    }
}
