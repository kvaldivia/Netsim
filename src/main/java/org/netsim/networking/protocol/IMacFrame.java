package org.netsim.networking.protocol;

public interface IMacFrame extends IDataUnit {
  public String getDestinationAddress();
  public String getSourceAddress();
  public IPacket getPayload();
}
