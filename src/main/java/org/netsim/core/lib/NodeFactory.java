package org.netsim.core.lib;

import org.netsim.core.contracts.Node;
import org.netsim.core.models.AccessPoint;
import org.netsim.core.models.ConsumerDevice;

public class NodeFactory 
{
    private static NodeFactory instance = null;

    private NodeFactory()
    {
    }

    public static NodeFactory getReference()
    {
        if (instance == null)
        {
            instance = new NodeFactory();
        }

        return instance;
    }

    public static Node createAccessPoint(String id, String name) {
        return new AccessPoint(id, name);
    }

    public static Node createConsumerDevice(String id, String name) {
        return new ConsumerDevice(id, name);
    }
}
