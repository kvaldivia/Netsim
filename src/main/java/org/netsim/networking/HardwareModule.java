package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.hardware.IHardwareInterface;
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
  IHardwareInterface<? extends IFrame> provideEthernetInterface(EthernetInterface eface) {
    return eface;
  }

  @Provides
  @Named("wireless")
  IHardwareInterface<? extends IFrame> provideWlanInterface(WlanInterface wface) {
    return wface;
  }

  @Provides
  @Named("wired")
  public ILink<? extends IHardwareInterface<? extends IFrame>> 
    provideWiredLink(WiredLink wired) {
    return wired;
  }

  @Provides
  @Named("wireless")
  public ILink<? extends IHardwareInterface<? extends IFrame>> 
    provideWirelessLink(WirelessLink wireless) {
    return wireless;
  }
}
