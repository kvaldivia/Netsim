package org.netsim.networking.device;

import org.netsim.networking.protocol.IMessage;

public interface IConsumerDevice extends IDevice {

  public void receiveMessage(IMessage msg, String from);

  public void sendMessage(IMessage msg, String from);

}
