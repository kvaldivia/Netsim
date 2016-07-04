package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.device.ADevice;
import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.protocol.IFrame;

import dagger.Component;

@Component(modules = {DeviceModule.class, HardwareInterfaceModule.class, NetworkModule.class})
public interface NetworkingComponent {
  @Named("ethernet")
  AHardwareInterface<? extends IFrame> provideEthernetInterface();

  @Named("wireless")
  AHardwareInterface<? extends IFrame> provideWlanInterface();

  ADevice<? extends IFrame> provideAccessPoint();

  Network provideNetwork();
}
