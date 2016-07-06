package org.netsim.networking.protocol;

import javax.inject.Inject;

public class EthernetFrame implements IFrame {
  private IMacFrame payload;
  private String destinationMac;
  private String sourceMac;
  private String frameCheckSequence;

  @Inject
  public EthernetFrame(IMacFrame pl, String srcMac, String destMac) {
    sourceMac = srcMac;
    destinationMac = destMac;
    payload = pl;
  }

  public EthernetFrame() {

  }
  
  public String getSourceAddress() {
    return this.sourceMac;
  }

  public String getDestinationAddress() {
    return this.destinationMac;
  }

  public IMacFrame getPayload() {
    return this.payload;
  }

  public String getFcs() {
    return this.frameCheckSequence;
  }

}
