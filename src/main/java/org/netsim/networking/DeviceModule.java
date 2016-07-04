package org.netsim.networking;

import org.netsim.networking.device.ADevice;
import org.netsim.networking.device.AccessPoint;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.IFrame;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceModule {
  @Provides
  ADevice<? extends IFrame> provideAccessPoint(WlanInterface wlan, EthernetInterface eth) {
    AccessPoint ap = new AccessPoint();
    ap.addWlanInterface(wlan);
    ap.addEthernetInterface(eth);
    return ap;
  }
}
