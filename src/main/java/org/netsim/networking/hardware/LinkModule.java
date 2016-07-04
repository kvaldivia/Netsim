package org.netsim.networking.hardware;

import org.netsim.networking.protocol.IFrame;

import dagger.Module;
import dagger.Provides;

@Module
public class LinkModule {
  private WlanInterface gateway;

  public <GW extends IHardwareInterface<? extends IFrame>>
  void setGateway(GW wlan) {
    gateway = (WlanInterface) wlan;
  }

  @Provides
  public ILink<? extends IHardwareInterface<? extends IFrame>> 
  provideWiredLink(WiredLink wired) {
    return wired;
  }

  @Provides
  public ILink<? extends IHardwareInterface<? extends IFrame>> 
  provideWirelessLink() {
    return new WirelessLink(gateway);
  }
}
