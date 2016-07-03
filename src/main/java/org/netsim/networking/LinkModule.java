package org.netsim.networking;

import dagger.Module;
import dagger.Provides;

@Module
public class LinkModule {
  private WlanInterface gateway;

  public <GW extends AHardwareInterface<? extends IFrame>>
  void setGateway(GW wlan) {
    gateway = (WlanInterface) wlan;
  }

  @Provides
  public ILink<? extends AHardwareInterface<? extends IFrame>> 
  provideWiredLink(WiredLink wired) {
    return wired;
  }

  @Provides
  public ILink<? extends AHardwareInterface<? extends IFrame>> 
  provideWirelessLink() {
    return new WirelessLink(gateway);
  }
}
