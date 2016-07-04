package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.device.AccessPoint;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.WlanInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceModule {
  @Provides
  @Named("ap")
  AccessPoint  provideAccessPoint(WlanInterface wlan, EthernetInterface eth) {
    AccessPoint ap = new AccessPoint();
    ap.addWlanInterface(wlan);
    ap.addEthernetInterface(eth);
    return ap;
  }
}
