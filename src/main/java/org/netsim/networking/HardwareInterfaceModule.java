package org.netsim.networking;

import dagger.Module;
import dagger.Provides;

@Module
public class HardwareInterfaceModule {
  @Provides
  EthernetInterface provideEthernetInterface() {
    return new EthernetInterface();
  }

  @Provides
  WlanInterface provideWlanInterface() {
    return new WlanInterface();
  }
}
