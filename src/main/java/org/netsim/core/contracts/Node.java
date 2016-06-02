package org.netsim.core.contracts;

import java.util.HashMap;
import org.netsim.utils;

public interface Node {
    public String getName();
    public String getId();
    public void sendMessageTo(String ipAddress, String message);
    public Interface getInterfaceById(String id);
    public HashMap<String, Interface> listInterfaces();
    public ForwardingTable getForwardingTable();
    public MessageHistory getMessageHistory();
    public void receiveMessage(String message);
    public void appendRoute(Link link);
    public void removeRoute(Link link);
}
