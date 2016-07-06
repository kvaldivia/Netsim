package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.WiredLink;
import org.netsim.networking.hardware.WirelessLink;
import org.netsim.networking.hardware.WlanInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class HardwareModule {
  @Provides
  @Named("ethernet")
  EthernetInterface provideEthernetInterface(EthernetInterface eface) {
    return eface;
  }

  @Provides
  @Named("wlan")
  WlanInterface provideWlanInterface(WlanInterface wface) {
    return wface;
  }

  @Provides
  @Named("wired")
  WiredLink provideWiredLink(WiredLink wired) {
    return wired;
  }

  @Provides
  @Named("wireless")
  WirelessLink provideWirelessLink(WirelessLink wireless) {
    return wireless;
  }
}
