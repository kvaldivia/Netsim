package org.netsim.device;

import org.netsim.networking.EthernetInterface;
import org.netsim.networking.WlanInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceModule {
  @Provides
  AccessPoint provideAccessPoint() {
    AccessPoint ap = new AccessPoint();
    ap.addWlanInterface(new WlanInterface());
    ap.addEthernetInterface(new EthernetInterface());
    return ap;
  }
}
