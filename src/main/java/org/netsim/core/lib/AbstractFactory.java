package org.netsim.core.lib;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.Port;
import org.netsim.core.contracts.StackStep;

public abstract class AbstractFactory {
    public abstract Interface getWlanInterface(String id, String ip, StackStep up, StackStep down);
    public abstract Interface getEthInterface(String id, String ip, StackStep up, StackStep down);
    public abstract Port getWlanPort(String mac, StackStep up, StackStep down);
    public abstract Port getEthPort(String mac, StackStep up, StackStep down);
}
