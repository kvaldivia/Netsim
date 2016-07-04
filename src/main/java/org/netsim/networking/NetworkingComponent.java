package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.device.AccessPoint;
import org.netsim.networking.hardware.IHardwareInterface;
import org.netsim.networking.hardware.ILink;
import org.netsim.networking.protocol.IFrame;

import dagger.Component;

@Component(modules = {DeviceModule.class, HardwareModule.class})
public interface NetworkingComponent {
  @Named("ethernet")
  IHardwareInterface<? extends IFrame> provideEthernetInterface();

  @Named("wireless")
  IHardwareInterface<? extends IFrame> provideWlanInterface();

  @Named("wired")
  public ILink<? extends IHardwareInterface<? extends IFrame>> 
    provideWiredLink();

  @Named("wireless")
  public ILink<? extends IHardwareInterface<? extends IFrame>> 
    provideWirelessLink();

  @Named("ap")
  AccessPoint provideAccessPoint();


  AccessPoint ap();
}
