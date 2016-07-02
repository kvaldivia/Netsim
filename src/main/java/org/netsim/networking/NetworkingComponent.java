package org.netsim.networking;

import dagger.Component;

@Component(modules = {HardwareInterfaceModule.class})
public interface NetworkingComponent {
  EthernetInterface provideEthernetInterface();

  WlanInterface provideWlanInterface();
}
