package org.netsim.simulation.node;

import java.awt.Point;
import java.util.HashMap;

import javax.inject.Inject;

import org.netsim.networking.device.IDevice;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;

public class WalkingNode implements INode {
  private HashMap<IHardwareInterface<? extends IFrame>, Double> coverageDistances;
  private Point point;
  private IDevice<? extends IDataUnit> device;

  @Inject
  public WalkingNode(IDevice<? extends IDataUnit> dev) {
    device = dev;
    point = null;
    coverageDistances = new HashMap<>();
    for (IHardwareInterface<? extends IFrame> iface: dev.listInterfaces()) {
      coverageDistances.put(iface, iface.getCoverageDistance());
    }
  }

  @Override
  public void setPoint(Point p) {
    if (point == null)
      point = p;
  }

  @Override
  public IDevice<? extends IDataUnit> getDevice() {
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
  public HashMap<IHardwareInterface<? extends IFrame>, Double> getCoverageDistances() {
    // TODO Auto-generated method stub
    return null;
  }

}
