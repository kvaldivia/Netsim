package org.netsim.core.contracts;

import java.util.List;

public interface Node {
    String getName();
    String getId();
    List<Interface> listInterfaces();
    Interface getInterfaceById(String id);
    void sendMessageTo(String destIp, String message);
    void receiveMessage(String sourceIp, String message);
    List<Port> listPorts();
    Port getPortById(String id);
    void buildNetworkLayer(Node node);
    void buildDataLinkLayer(Node node);
    void createInterface(String id, String ipAdd, String macAdd);
}
