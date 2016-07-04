package org.netsim.simulation.plane;

import java.util.ArrayList;
import java.util.HashMap;

import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.ILink;
import org.netsim.networking.protocol.IFrame;
import org.netsim.simulation.SimulationComponent;
import org.netsim.simulation.node.INode;

public interface IPlane {
  HashMap<String, INode> listNodes();

  ArrayList<ILink<? extends IHardwareInterface<? extends IFrame>>> listLinks();

  <HW extends IHardwareInterface<IFrame>>
    void createLink(HW eth0, HW eth1);

  void setSimComponent(SimulationComponent simCom);

  void createAccessPoint();

  void createSmartphone();

  void createLaptop();
}
