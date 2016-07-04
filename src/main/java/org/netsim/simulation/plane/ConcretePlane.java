package org.netsim.simulation.plane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.ILink;
import org.netsim.networking.protocol.IFrame;
import org.netsim.simulation.SimulationComponent;
import org.netsim.simulation.node.INode;

public class ConcretePlane implements IPlane {
  private ArrayList<ILink<? extends IHardwareInterface<? extends IFrame>>> links;
  private HashMap<String, INode> nodes;
  private SimulationComponent simComponent;

  @Inject
  public ConcretePlane(SimulationComponent sim) {
    links = new ArrayList<>();
    nodes = new HashMap<>();
    simComponent = sim;
  }

  @Override
  public <HW extends IHardwareInterface<IFrame>> 
  void createLink(HW eth0, HW eth1) {

  }

  @Override
  public ArrayList<ILink<? extends IHardwareInterface<? extends IFrame>>> listLinks() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, INode> listNodes() {
    HashMap<String,INode> result = new HashMap<>(nodes.size());
    
    for (Map.Entry<String,INode> entry: nodes.entrySet()) {
      result.put(entry.getKey(), entry.getValue());
    }
    return result;
  }

  @Override
  public void createLaptop() {
    // TODO Auto-generated method stub

  }

  @Override
  public void createAccessPoint() {
    INode node = simComponent.provideStandingNode();
    nodes.put("shit", node);
  }

  @Override
  public void createSmartphone() {
    // TODO Auto-generated method stub

  }

  @Override
  public void setSimComponent(SimulationComponent simCom) {
    // TODO Auto-generated method stub
    if (simComponent == null) {
      simComponent = simCom;
    }

  }

}
