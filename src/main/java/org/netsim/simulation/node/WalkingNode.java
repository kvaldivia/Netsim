package org.netsim.simulation.node;

import java.awt.Point;
import java.util.HashMap;

import javax.inject.Inject;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.hardware.IHardwareInterface;

public class WalkingNode implements INode {
  private HashMap<IHardwareInterface, Double> coverageDistances;
  private Point point;
  private IDevice device;

  @Inject
  public WalkingNode(IDevice dev) {
    device = dev;
    point = null;
    coverageDistances = new HashMap<>();
    for (IHardwareInterface iface: dev.listInterfaces()) {
      coverageDistances.put(iface, iface.getCoverageDistance());
    }
  }

  @Override
  public void setPoint(Point p) {
    if (point == null)
      point = p;
  }

  @Override
  public IDevice getDevice() {
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
  public HashMap<IHardwareInterface, Double> getCoverageDistances() {
    // TODO Auto-generated method stub
    return null;
  }

}
