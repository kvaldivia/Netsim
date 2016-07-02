package org.netsim.networking;

public interface IPacket extends IDataUnit {
  public IDataUnit getPayload();

  public String getSourceAddress();

  public String getDestinationAddress();
}
