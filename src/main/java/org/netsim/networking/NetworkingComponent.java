package org.netsim.networking;

import dagger.Component;

@Component(modules = {DeviceModule.class, HardwareInterfaceModule.class, NetworkModule.class})
public interface NetworkingComponent {
  EthernetInterface provideEthernetInterface();

  WlanInterface provideWlanInterface();

  AccessPoint provideAccessPoint();

  Network provideNetwork();
}
