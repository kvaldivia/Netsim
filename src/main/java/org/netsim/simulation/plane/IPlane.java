package org.netsim.simulation.plane;

import java.util.ArrayList;
import java.util.HashMap;

import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.ILink;
import org.netsim.simulation.SimulationComponent;
import org.netsim.simulation.node.INode;

public interface IPlane {
  HashMap<String, INode> listNodes();

  ArrayList<ILink<? extends IHardwareInterface>> listLinks();

  void createLink(IHardwareInterface eth0, IHardwareInterface eth1);

  void setSimComponent(SimulationComponent simCom);

  void createAccessPoint();

  void createSmartphone();

  void createLaptop();
}
