package org.netsim.simulation;

import org.netsim.simulation.plane.IPlane;

import dagger.Component;

@Component(modules = {PlaneModule.class})
public interface PlaneComponent {
  IPlane provideConcretePlane();
}
