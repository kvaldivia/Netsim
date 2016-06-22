package org.netsim.core;

import java.util.HashMap;

public abstract class Device {
    private HashMap<String, Interface> interfaces;
    private Protocol internetProtocol;
    private Protocol llcProtocol;
    private Protocol arpProtocol;

    public abstract void sendMessage(String destAddress, String message);
    public abstract void receiveMessage(String sourceAddress, String message);
}
