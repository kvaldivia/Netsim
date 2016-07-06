package org.netsim.simulation.node;

import java.awt.Point;
import java.util.HashMap;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.hardware.IHardwareInterface;

public interface INode {
  void tick();

  Point getPoint();

  IDevice getDevice();

  void setPoint(Point p);

  HashMap<IHardwareInterface, Double> getCoverageDistances();
}
