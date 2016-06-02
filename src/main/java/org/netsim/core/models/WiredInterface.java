package org.netsim.core.models;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.Node;
import org.netsim.core.contracts.Link;
import org.netsim.core.lib.LinkFactory;

public class WiredInterface implements Interface{
    private Node node;
    private String ipAddress;
    private String macAddress;
    private Link link;
    private LinkFactory linkFactory;

    public WiredInterface(Node node, String ipAddress) {
        this.node = node;
        this.ipAddress = ipAddress;
        this.macAddress = "FF:FF:FF:FF";
        this.linkFactory = LinkFactory.getReference();
    }

    public Node getNode() {
        return this.node;
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

    public void linkWith(Interface remote) {
        this.link = this.linkFactory.createWired(this, remote);
    }

    public void unlink(Interface remote) {
        this.link.remove(remote);
    }

}
