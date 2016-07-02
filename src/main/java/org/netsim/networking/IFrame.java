package org.netsim.networking;

public interface IFrame extends IDataUnit {
  public String getSourceAddress();

  public String getDestinationAddress();

  public IPacket getPayload();

  public String getFcs();

  public String getProtocol();
}
