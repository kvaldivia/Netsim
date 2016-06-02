package org.netsim.core.models;

import org.netsim.core.contracts.Node;
import java.util.HashMap;

public class ConsumerDevice {
    private String id;
    private String name;
    private int numInterfaces;
    private HashMap<String, Interface> interfaces;
    private ForwardingTable forwardingTable;
    private MessageHistory messageHistory;

    public ConsumerDevice(String id, String name) {
        this.id = id;
        this.name = name;
        this.numInterfaces = 1;
        this.interfaces = new HashMap<String, Interface>(this.numInterfaces);
        this.forwardingTable = new ForwardingTable();
        this.messageHistory = new MessageHistory();
    }

    public String getName() {
        return this.name;
    }

    public Interface getInterfaceById(String interfaceId) {
        return this.interfaces.get(interfaceId);
    }

    public String getId() {
        return this.id;
    }

    public HashMap<String, Interface> listInterfaces() {
        return this.interfaces;
    }

    public ForwardingTable getForwardingTable() {
        return this.forwardingTable;
    }

    public MessageHistory getMessageHistory() {
        return this.messageHistory;
    }

    public void removeRoute(Link link) {
        return;
    }

    public void appendRoute(Link link) {
        return;
    }
}
