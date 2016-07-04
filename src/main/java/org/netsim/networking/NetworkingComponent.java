package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.device.AccessPoint;
import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.hardware.ILink;
import org.netsim.networking.protocol.IFrame;

import dagger.Component;

@Component(modules = {DeviceModule.class, HardwareModule.class})
public interface NetworkingComponent {
  @Named("ethernet")
  AHardwareInterface<? extends IFrame> provideEthernetInterface();

  @Named("wireless")
  AHardwareInterface<? extends IFrame> provideWlanInterface();

  @Named("wired")
  public ILink<? extends AHardwareInterface<? extends IFrame>> 
    provideWiredLink();

  @Named("wireless")
  public ILink<? extends AHardwareInterface<? extends IFrame>> 
    provideWirelessLink();

  @Named("ap")
  AccessPoint provideAccessPoint();


  AccessPoint ap();
}
