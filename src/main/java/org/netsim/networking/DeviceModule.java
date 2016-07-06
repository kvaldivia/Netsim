package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.device.AccessPoint;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.SecondLayerStack;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceModule {
  @Provides
  @Named("ap")
  AccessPoint provideAccessPoint(SecondLayerStack two, 
      WlanInterface wlan, EthernetInterface eth) {

    AccessPoint ap = new AccessPoint(two);
    ap.addWlanInterface(wlan);
    ap.addEthernetInterface(eth);
    return ap;
  }
}
