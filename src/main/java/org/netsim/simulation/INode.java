package org.netsim.simulation;

import java.awt.Point;
import java.util.HashMap;

import org.netsim.networking.device.ADevice;
import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;

public interface INode {
  void tick();

  Point getPoint();

  ADevice<? extends IDataUnit> getDevice();

  void setPoint(Point p);

  HashMap<AHardwareInterface<? extends IFrame>, Double> getCoverageDistances();
}
