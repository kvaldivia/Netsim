package org.netsim.core.models;

import org.netsim.core.contracts.AbstractNode;
import org.netsim.core.contracts.PhysicalInterface;
import org.netsim.core.contracts.ProtocolLayer;
import org.netsim.core.contracts.Link;
import org.netsim.core.contracts.StackStep;

import java.util.ArrayList;

public class ConsumerDevice extends AbstractNode {
    private String id;
    private ArrayList<PhysicalInterface> interfaces;


    private ConsumerDevice(ConsumerDeviceBuilder builder) {
        this.id = builder.id;
    }

    public String getId() {
        return this.id;
    }

    public PhysicalInterface getInterfaceById(String id) {
        for (PhysicalInterface result : this.interfaces)
        {
            if(result.getId() == id)
                return result;
        }
        return null;
    }

    public ArrayList<PhysicalInterface> listInterfaces() {
        return this.interfaces;
    }

    public void receiveMessage(String sourceIp, String message) {
        // TODO: define a MessageHistoryTable; 
    }

    public void sendMessageTo(String address, String message) {
    }

    public void getForwardingTable() {
        // TODO: define a ForwardingtTable
    }

    public void getRoutingTable() {
        // TODO: define  a RoutingTable
    }

    public static class ConsumerDeviceBuilder {
        private StackStep protocolStack;
        private String id;
        private ArrayList<PhysicalInterface> wlan;
        private ArrayList<PhysicalInterface> ethernet;
        private ArrayList<PhysicalInterface> interfaces;

        public ConsumerDeviceBuilder(StackStep layer)
        {
            this.id = "WeAreAllTheSame";
            this.wlan = new ArrayList<PhysicalInterface>();
            this.ethernet = new ArrayList<PhysicalInterface>();
            this.interfaces = new ArrayList<PhysicalInterface>();
        }

        public ConsumerDevice build() 
        {
            this.interfaces.addAll(ethernet);
            this.interfaces.addAll(wlan);
            return new ConsumerDevice(this);
        }

        public ConsumerDeviceBuilder createWlan(int coverage) 
        {
            String id = String.valueOf(this.wlan.size());
            //wlan = InterfaceFactory.getWlanInterface(coverage);
            return this;
        }

        public ConsumerDeviceBuilder createEthLan() 
        {
            return this;
        }

    }
}
