package org.netsim.networking.protocol;

public class MacFrame implements IMacFrame {
  private String destinationAddress;
  private String sourceAddress;
  private IPacket payload;

  public MacFrame(IPacket pay, String src, String dest) {
    payload = pay;
    sourceAddress = src;
    destinationAddress = dest;
  }

  @Override
  public String getDestinationAddress() {
    // TODO Auto-generated method stub
    return destinationAddress;
  }

  @Override
  public String getSourceAddress() {
    // TODO Auto-generated method stub
    return sourceAddress;
  }

  @Override
  public IPacket getPayload() {
    // TODO Auto-generated method stub
    return payload;
  }

}
