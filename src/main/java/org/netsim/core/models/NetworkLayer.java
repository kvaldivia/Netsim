package org.netsim.core.models;

import java.util.List;

import org.netsim.core.contracts.Interface;
import org.netsim.core.contracts.ProtocolLayer;
import org.netsim.core.contracts.Pdu;
import org.netsim.core.contracts.Node;

public class NetworkLayer {
    private Node node;
    private StackStep upperStep;
    private StackStep lowerStep;
    private List<Interface> interfaces;

    private NetworkLayer(NetworkLayerBuilder builder) {
        this.node = node;
        this.upperStep = upperStep;
        this.lowerStep = lowerStep;
        this.interfaces.addAll(interfaces);
    }

    public Node getNode() {
        return this.node;
    }

    public List<Interface> listInterfaces() {
        return this.interfaces;
    }

    public Interface getInterfaceById(String id) {
        return interfaces.get(0);
    }

    public static class NetworkLayerBuilder {
        private final Node node;
        private final StackStep upperStep;
        private final StackStep lowerStep;
        private List<Interface> interfaces;

        public NetworkLayerBuilder(Node node, StackStep upper, StackStepp lower) {
            this.node = node;
            this.upperStep = upper;
            this.lowerStep = lower;
            this.interfaces = new List<Interface>();
        }

        public NetworkLayer build() {
            return new NetworkLayer(this);
        }

    }
}
