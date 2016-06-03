package org.netsim.core.lib;

import org.netsim.core.contracts.StackStep;
import org.netsim.core.contracts.Port;
import org.netsim.core.contracts.Interface;
import org.netsim.core.models.WlanInterface;
import org.netsim.core.models.EthernetInterface;


public class InterfaceFactory extends AbstractFactory {

    public InterfaceFactory(){}

    public Interface getWlanInterface(String id, String ip, StackStep upperStep, StackStep lowerStep) {
        return new WlanInterface(id, ip, upperStep, lowerStep);
    }

    public Interface getEthInterface(String id, String ip, StackStep upperStep, StackStep lowerStep) {
        return new EthernetInterface(id, ip, upperStep, lowerStep);
    }

    public Port getWlanPort(String mac, StackStep upperStep, StackStep lowerStep) {
        return null;
    }

    public Port getEthPort(String mac, StackStep upperStep, StackStep lowerStep) {
        return null;
    }
}
