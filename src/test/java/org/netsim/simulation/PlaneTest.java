package org.netsim.simulation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.netsim.networking.device.IDevice;
import org.netsim.simulation.node.INode;
import org.netsim.simulation.plane.IPlane;

public class PlaneTest {

  private PlaneComponent planeComponent;
  private IPlane plane;
  private ArrayList<INode> nodes;
 
  @Before
  public void initialize() {
    planeComponent = DaggerPlaneComponent
      .builder()
      .planeModule(new PlaneModule(50,50))
      .build();

    plane = planeComponent.provideConcretePlane();
  }

  @Test
  public void itShouldCreateNodeContainingAccessPoint() {
    nodes = new ArrayList<>();
    plane.createAccessPoint();
    nodes.addAll(plane.listNodes().values());
    System.out.println(plane.listNodes().size());
    IDevice dev;
    for (INode node: nodes) {
      dev = node.getDevice();
      assertNotNull(dev);
    }
  }

  @Test
  public void theAccessPointShouldHaveOneWirelessLanInterface() {
    nodes = new ArrayList<>();
    nodes.addAll(plane.listNodes().values());
    IDevice  dev;
    for (INode node: nodes) {
      dev = node.getDevice();
      assertTrue(dev.listWirelessInterfaces().size() == 1);
    }
  }

}
