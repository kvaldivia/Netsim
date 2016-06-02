package org.netsim.core.models;

import org.netsim.core.contracts.Link;
import org.netsim.core.contracts.Interface;

import java.util.ArrayList;

public class WiredLink implements Link {
    private ArrayList<Interface> interfaces;

    public WiredLink(Interface source, Interface dest) {
        source.setLink(this);
        this.interfaces.add(source);
        dest.setLink(this);
        this.interfaces.add(dest);
    }

    public void transport(Interface source, Interface dest, String frame) {
        dest.receiveFrame(source, frame);
    }
}
