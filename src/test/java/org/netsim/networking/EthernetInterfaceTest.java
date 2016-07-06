package org.netsim.networking;

import org.junit.Before;
import org.netsim.networking.hardware.EthernetInterface;
import org.netsim.networking.protocol.IPacket;
import org.netsim.networking.protocol.IpPacket;

public class EthernetInterfaceTest{
  private IPacket packet;
  private EthernetInterface ethInterface;
  private String srcAddress;
  private String destAddress;
  private String consumerId;
  private NetworkingComponent netComponent;
 
  @Before
  public void initialize() {
    packet = new IpPacket();
    ethInterface = new EthernetInterface();
    srcAddress = ethInterface.getAddress();
    destAddress = "AA:AA:AA:AA:AA:AA";
    consumerId = "IP";
    netComponent = DaggerNetworkingComponent.builder()
      .hardwareModule(new HardwareModule())
      .build();
  }
  

}
