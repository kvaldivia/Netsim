package org.netsim.networking.protocol;

public class WlanFrame implements IFrame {
  private String destinationMac;
  private String sourceMac;
  private String gatewayAddress;
  private IMacFrame payload;
  private String frameCheckSequence;

  public WlanFrame(IMacFrame pl, String srcMac, String destMac, String gateway) {
    sourceMac = srcMac;
    destinationMac = destMac;
    gatewayAddress = gateway;
    payload = pl;
  }

  public WlanFrame() {

  }
  
  public String getSourceAddress() {
    return sourceMac;
  }

  public String getDestinationAddress() {
    return destinationMac;
  }

  public IMacFrame getPayload() {
    return payload;
  }
  
  public String getGatewayAddress() {
    return gatewayAddress;
  }

  public String getFcs() {
    return frameCheckSequence;
  }
}
