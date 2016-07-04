package org.netsim.simulation.node;

import java.awt.Point;
import java.util.HashMap;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;

public interface INode {
  void tick();

  Point getPoint();

  IDevice<? extends IDataUnit> getDevice();

  void setPoint(Point p);

  HashMap<IHardwareInterface<? extends IFrame>, Double> getCoverageDistances();
}
