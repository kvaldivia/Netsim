package org.netsim.networking.protocol;

public class IpPacket implements IPacket {
  private String sourceAddress;
  private String destinationAddress;
  private Message message;

  public IpPacket(String source, String dest, Message msg) {
    sourceAddress = source;
    destinationAddress = dest;
    message = msg;
  }

  public IpPacket() {

  }

  public String getSourceAddress() {
    return sourceAddress;
  }

  public String getDestinationAddress() {
    return destinationAddress;
  }

  public Message getPayload() {
    return message;
  }
}
