package org.netsim.core.lib;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.Link;
import org.netsim.core.models.WirelessLink;
import org.netsim.core.models.WiredLink;

public class LinkFactory {
    private static Network network;
    private static LinkFactory instance = null;
    
    private LinkFactory() {
    }

    public static LinkFactory getReference() {
        if (instance == null)
            instance = new LinkFactory();
        return instance;
    }

    public static Link createLink(Interface wlan1) {
        WirelessLink link = new WirelessLink(wlan1);
        network.addLink(link);
        return link;
    }

    public static Link createLink(Interface eth0, Interface eth1) {
        WiredLink link = new WiredLink(eth0, eth1);
        network.addLink(link);
        return new WiredLink(eth0, eth1);
    }
}
