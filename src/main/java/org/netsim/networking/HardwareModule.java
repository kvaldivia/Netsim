package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.ILink;
import org.netsim.networking.hardware.WiredLink;
import org.netsim.networking.hardware.WirelessLink;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IFrame;

import dagger.Module;
import dagger.Provides;

@Module
public class HardwareModule {
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

  @Provides
  @Named("wired")
  public ILink<? extends AHardwareInterface<? extends IFrame>> 
    provideWiredLink(WiredLink wired) {
    return wired;
  }

  @Provides
  @Named("wireless")
  public ILink<? extends AHardwareInterface<? extends IFrame>> 
    provideWirelessLink(WirelessLink wireless) {
    return wireless;
  }
}
