package org.netsim.core.models;

import java.util.ArrayList;

import org.netsim.core.contracts.Link;
import org.netsim.core.contracts.Interface;

public class WirelessLink implements Link {
    private Interface gateway;
    private ArrayList<Interface> hosts;

    public WirelessLink(Interface wlan) {
        setGateway(wlan);
    }

    private void setGateway(Interface wlan) {
        this.gateway = wlan;
        wlan.setLink(this);
    }

    public void transport(Interface src, Interface dest, String frame) {
        dest.receiveFrame(src, frame);
    }

    public void addInterface(Interface wlan) {
        this.hosts.add(wlan);
        wlan.setLink(this);
    }

    public void removeInterface(Interface wlan) {
        wlan.removeLink();
        this.hosts.remove(wlan);
    }

}
