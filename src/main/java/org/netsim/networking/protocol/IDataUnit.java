package org.netsim.networking.protocol;

public interface IDataUnit {
  public IDataUnit getPayload();
  
  public String getSourceAddress();

  public String getDestinationAddress();
}
