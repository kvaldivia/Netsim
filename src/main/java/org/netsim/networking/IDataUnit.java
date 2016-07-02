package org.netsim.networking;

public interface IDataUnit {
  public IDataUnit getPayload();
  
  public String getSourceAddress();

  public String getDestinationAddress();
}
