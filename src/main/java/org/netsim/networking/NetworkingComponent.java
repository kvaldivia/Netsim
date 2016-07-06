package org.netsim.networking;

import javax.inject.Named;

import org.netsim.networking.device.AccessPoint;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.hardware.WiredLink;
import org.netsim.networking.hardware.WirelessLink;
import org.netsim.networking.hardware.WlanInterface;
import org.netsim.networking.protocol.ConsumerMacProtocol;
import org.netsim.networking.protocol.ForwardMacProtocol;
import org.netsim.networking.protocol.IProtocolStack;
import org.netsim.networking.protocol.InternetProtocol;
import org.netsim.networking.protocol.SwitchMacProtocol;

import dagger.Component;

@Component(modules = 
  { 
     HardwareModule.class, 
     ProtocolModule.class, 
     DeviceModule.class,
  }
)
public interface NetworkingComponent {

  @Named("ethernet")
  EthernetInterface provideEthernetInterface();

  @Named("wlan")
  WlanInterface provideWlanInterface();

  @Named("wired")
  WiredLink provideWiredLink();

  @Named("wireless")
  WirelessLink provideWirelessLink();

  ConsumerMacProtocol provideConsumerMac();

  ForwardMacProtocol provideForwardMac();

  SwitchMacProtocol provideSwitchMac();

  InternetProtocol provideInternet();

  IProtocolStack provideSecondLayer();

  AccessPoint ap();
}
