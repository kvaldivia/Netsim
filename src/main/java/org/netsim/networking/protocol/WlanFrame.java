package org.netsim.networking.protocol;

public class WlanFrame implements IFrame {
  private String destinationMac;
  private String sourceMac;
  private String gatewayAddress;
  private String protocol;
  private IPacket payload;
  private String frameCheckSequence;

  public WlanFrame(IPacket pl, String srcMac, String destMac, String gateway, String prot) {
    sourceMac = srcMac;
    destinationMac = destMac;
    gatewayAddress = gateway;
    payload = pl;
    protocol = prot;
  }

  public WlanFrame() {

  }
  
  public String getSourceAddress() {
    return sourceMac;
  }

  public String getDestinationAddress() {
    return destinationMac;
  }

  public IPacket getPayload() {
    return payload;
  }
  
  public String getGatewayAddress() {
    return gatewayAddress;
  }

  public String getProtocol() {
    return protocol;
  }

  public String getFcs() {
    return frameCheckSequence;
  }
}
