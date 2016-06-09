package org.netsim.core.contracts;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractNode {
    private String id;
    private ArrayList<PhysicalInterface> interfaces;
    private ProtocolLayer protocolStack;
    public abstract String getId();
    public abstract ArrayList<PhysicalInterface> listInterfaces();
    public abstract PhysicalInterface getInterfaceById(String id);
    public abstract void sendMessageTo(String destIp, String message);
    public abstract void receiveMessage(String sourceIp, String message);
}
