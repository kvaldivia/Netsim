package org.netsim.networking.protocol;

public interface IFrame extends IDataUnit {
  public String getSourceAddress();

  public String getDestinationAddress();

  public IMacFrame getPayload();

  public String getFcs();
}
