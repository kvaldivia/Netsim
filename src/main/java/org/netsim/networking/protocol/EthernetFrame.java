package org.netsim.networking.protocol;

import org.netsim.networking.protocol.IFrame;
import org.netsim.networking.protocol.IPacket;

public class EthernetFrame implements IFrame {
  private IPacket payload;
  private String destinationMac;
  private String sourceMac;
  private String protocol;
  private String frameCheckSequence;

  public EthernetFrame(IPacket pl, String srcMac, String destMac, String prot) {
    sourceMac = srcMac;
    destinationMac = destMac;
    payload = pl;
    protocol = prot;
  }

  public EthernetFrame() {

  }
  
  public String getSourceAddress() {
    return this.sourceMac;
  }

  public String getDestinationAddress() {
    return this.destinationMac;
  }

  public IPacket getPayload() {
    return this.payload;
  }

  public String getFcs() {
    return this.frameCheckSequence;
  }

  public String getProtocol() {
    return protocol;
  }
}
