package org.netsim.networking.protocol;

public interface IFrame extends IDataUnit {
  public String getSourceAddress();

  public String getDestinationAddress();

  public IPacket getPayload();

  public String getFcs();

  public String getProtocol();
}
