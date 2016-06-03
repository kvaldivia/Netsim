package org.netsim.core.lib;

public class FactoryProducer {

    public FactoryProducer() {

    }

    public AbstractFactory getInterfaceFactory() {
        return new InterfaceFactory();
    }

    public AbstractFactory getPortFactory() {
        return new PortFactory();
    }
}
