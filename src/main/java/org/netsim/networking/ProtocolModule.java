package org.netsim.networking;

import org.netsim.networking.protocol.ConsumerMacProtocol;
import org.netsim.networking.protocol.ForwardMacProtocol;
import org.netsim.networking.protocol.IMacProtocol;
import org.netsim.networking.protocol.INetworkProtocol;
import org.netsim.networking.protocol.IProtocolStack;
import org.netsim.networking.protocol.InternetProtocol;
import org.netsim.networking.protocol.SecondLayerStack;
import org.netsim.networking.protocol.SwitchMacProtocol;

import dagger.Module;
import dagger.Provides;

@Module
public class ProtocolModule {
  @Provides
  IMacProtocol provideConsumerMac(ConsumerMacProtocol mac) {
    return mac;
  }

  @Provides
  IMacProtocol provideForwardMac(ForwardMacProtocol mac) {
    return mac;
  }

  @Provides
  IMacProtocol provideSwitchMac(SwitchMacProtocol mac) {
    return mac;
  }

  @Provides
  INetworkProtocol provideInternet(InternetProtocol ip) {
    return ip;
  }

  @Provides
  IProtocolStack provideSecondLayer(SecondLayerStack two) {
    return two;
  }
}
