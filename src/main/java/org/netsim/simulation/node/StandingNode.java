package org.netsim.simulation.node;

import java.awt.Point;
import java.util.HashMap;

import javax.inject.Inject;

import org.netsim.networking.device.ADevice;
import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.protocol.IDataUnit;
import org.netsim.networking.protocol.IFrame;

public class StandingNode implements INode {
  private HashMap<AHardwareInterface<? extends IFrame>, Double> coverageDistances;
  private Point point;
  private ADevice<? extends IDataUnit> device;


  @Inject
  public StandingNode(ADevice<? extends IDataUnit> dev) {
    device = dev;
    point = null;
    coverageDistances = new HashMap<>();
    for (AHardwareInterface<? extends IFrame> iface: dev.listInterfaces()) {
      coverageDistances.put(iface, iface.DOUBLE_COVERAGE_DISTANCE);
    }
  }

  @Override
  public HashMap<AHardwareInterface<? extends IFrame>, Double> getCoverageDistances() {
    HashMap<AHardwareInterface<? extends IFrame>, Double> results;
    results = new HashMap<>();
    return results;
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
  public void setPoint(Point p) {
    if (point == null)
      point = p;
  }

  @Override
  public void tick() {
    // TODO Auto-generated method stub
  }

}
