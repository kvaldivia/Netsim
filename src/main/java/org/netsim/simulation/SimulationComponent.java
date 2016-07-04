package org.netsim.simulation;

import org.netsim.networking.NetworkingComponent;
import org.netsim.simulation.node.INode;

import dagger.Component;

@Component(modules = {NodeModule.class}, dependencies = {NetworkingComponent.class})
public interface SimulationComponent {
  INode provideStandingNode();
}
