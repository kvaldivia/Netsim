package org.netsim.simulation;

import org.netsim.networking.DaggerNetworkingComponent;
import org.netsim.networking.DeviceModule;
import org.netsim.networking.HardwareModule;
import org.netsim.networking.NetworkingComponent;
import org.netsim.simulation.plane.ConcretePlane;
import org.netsim.simulation.plane.IPlane;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaneModule {
  private final int X_BOUND;
  private final int Y_BOUND; 
  private final int MIN = 20;

  public PlaneModule(int x, int y) {
    X_BOUND = x < MIN ? MIN : x;
    Y_BOUND = y < MIN ? MIN : y;
    
  }

  @Provides
  IPlane provideConcretePlane() {
    NetworkingComponent netComponent = DaggerNetworkingComponent.builder()
      .deviceModule(new DeviceModule())
      .hardwareModule(new HardwareModule())
      .build();

    SimulationComponent simComponent = DaggerSimulationComponent.builder()
      .networkingComponent(netComponent)
      .nodeModule(new NodeModule())
      .build();

    ConcretePlane plane = new ConcretePlane(simComponent);
    return plane;
  }
}
