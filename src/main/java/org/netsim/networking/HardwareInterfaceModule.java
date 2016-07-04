package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IFrame;

import dagger.Module;
import dagger.Provides;

@Module
public class HardwareInterfaceModule {
  @Provides
  @Named("ethernet")
  AHardwareInterface<? extends IFrame> provideEthernetInterface(EthernetInterface eface) {
    return eface;
  }

  @Provides
  @Named("wireless")
  AHardwareInterface<? extends IFrame> provideWlanInterface(WlanInterface wface) {
    return wface;
  }
}
