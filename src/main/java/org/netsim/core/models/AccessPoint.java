package org.netsim.core;

import java.util.HashMap;

public class AccessPoint extends Device {
    private HashMap<String, Interface> interfaces;
    private Protocol llcProtocol;
    private Protocol arpProtocol;

    public void sendMessage(String destAddress, String message) {
        
    }

    public void receiveMessage(String sourceAddress, String message) {

    }
}
