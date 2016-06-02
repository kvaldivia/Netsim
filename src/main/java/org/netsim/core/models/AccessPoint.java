package org.netsim.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.Node;

public class AccessPoint implements Node {
    
    private String id;
    private String name;
    private int numInterfaces;
    private HashMap<String, Interface> interfaces;
    private InterfaceFactory interfaceFactory;
    private ForwardingTable forwardingTable;
    private MessageHistory messageHistory;
    private int countWired;
    private int countWireless;

    public AccessPoint(String id, String name, String ipAdd, int numInterfaces) {
        this.id = id;
        this.name = name;
        this.numInterfaces = numInterfaces + 1;
        this.countWired = 0;
        this.countWireless = 0;
        this.interfaceFactory = new InterfaceFactory();
        this.interfaces = new HashMap<String, Interface>(numInterfaces);
        this.forwardingTable = new ForwardingTable();
        this.messageHistory = new MessageHistory();
    }

    public void sendMessageTo(String destIpAddress, String message) {
        // TODO: lookup table, and call the Link object's transport method.
        // maybe the lookup mechanism should return a link that contains a
        // node with the destIpAddress
        // this.interfaces.get("X").transport(message);
    }

    public void receiveMessage(String ipAddress, Interface i, String message) {
        // TODO: receive a string from a remote node's interface
        // and append it to the messageHistory table.
        // this.messageHistory.put(ipAddress, i, message);
    }

    public void addWiredInterface(String ipAdd, String macAdd) {
        
        if (this.interfaces.size() == this.numInterfaces)
            return;

        String id = "eth" + this.countWired;
        this.countWired++;

        Interface wired = this.interfaceFactory.createWired(ipAdd, macAdd);
        this.interfaces.put(id, wired);
    }

    public void addWirelessInterface(String ipAdd, String macAdd, int numConnections) {

        if (this.interfaces.size() == this.numInterfaces)
            return;

        String id = "wlan" + this.wirelessCount;
        Interface wireless = 
            this.interfaceFactory.createWireless(this, ipAdd, macAdd, numConnections);

        this.interfaces.put(id, wireless);
    }

    public HashMap<String, Interface> listInterfaces() {
        return this.interfaces;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Interface getInterfaceById(String interfaceId) {
        return this.interfaces.get(interfaceId);
    }

    public ForwardingTable getForwardingTable() {
        return this.forwardingTable;
    }

    public MessageHistory getMessageHistory() {
        return this.messageHistory;
    }

    protected void appendRoute(Link link) {
        return;
    }

    protected void removeRoute(Link link) {
        return;
    }
}
