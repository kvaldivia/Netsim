package org.netsim.core.lib;

import org.netsim.core.contracts.Node;
import org.netsim.core.contracts.Interface;
import org.netsim.core.models.WirelessInterface;
import org.netsim.core.models.WiredInterface;

public class InterfaceFactory 
{
    private static InterfaceFactory instance = null;

    private InterfaceFactory() 
    {
    }

    public InterfaceFactory getReference() 
    {
        if(instance == null)
            instance = new InterfeceFactory();

        return instance;
    }

    public static Interface createWireless(Node node, String ipAdd
        , String macAdd, int numConn) 
    {
        return new WirelessInterface(node, ipadd, macAdd, numConn);
    }

    public static Interface createWired(Node node, String ipAdd
        , String macAdd)
    {
        return new WiredInterface(node, ipAdd, macAdd);
    }
}
