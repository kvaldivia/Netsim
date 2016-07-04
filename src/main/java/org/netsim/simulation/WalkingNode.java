package org.netsim.simulation;

import java.awt.Point;
import java.util.HashMap;

import javax.inject.Inject;

import org.netsim.networking.device.ADevice;
import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;

public class WalkingNode implements INode {
  private HashMap<AHardwareInterface<? extends IFrame>, Double> coverageDistances;
  private Point point;
  private ADevice<? extends IDataUnit> device;

  @Inject
  public WalkingNode(ADevice<? extends IDataUnit> dev) {
    device = dev;
    point = null;
    coverageDistances = new HashMap<>();
    for (AHardwareInterface<? extends IFrame> iface: dev.listInterfaces()) {
      coverageDistances.put(iface, iface.DOUBLE_COVERAGE_DISTANCE);
    }
  }

  @Override
  public void setPoint(Point p) {
    if (point == null)
      point = p;
  }

  @Override
  public ADevice<? extends IDataUnit> getDevice() {
    return device;
  }

  @Override
  public Point getPoint() {
    return point;
  }

  @Override
  public void tick() {
    // TODO Auto-generated method stub
  }

  @Override
  public HashMap<AHardwareInterface<? extends IFrame>, Double> getCoverageDistances() {
    // TODO Auto-generated method stub
    return null;
  }

}
