package org.netsim.core.lib;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.Port;
import org.netsim.core.contracts.StackStep;
import org.netsim.core.models.WlanPort;
import org.netsim.core.models.EthernetPort;


public class PortFactory extends AbstractFactory {
    public Interface getWlanInterface(String id, String ip, StackStep upperStep, StackStep lowerStep) {
        return null;
    }

    public Interface getEthInterface(String id, String ip, StackStep upperStep, StackStep lowerStep){
        return null;
    }

    public Port getWlanPort(String mac, StackStep upperStep, StackStep lowerStep) {
        return new WlanPort(mac, upperStep, lowerStep);
    }

    public Port getEthPort(String mac, StackStep upperStep, StackStep lowerStep) {
        return new EthernetPort(mac, upperStep, lowerStep);
    }
}
