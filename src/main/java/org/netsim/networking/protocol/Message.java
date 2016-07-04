package org.netsim.networking.protocol;

import java.util.HashMap;

public class Message implements IMessage {
  private String message;
  private HashMap<String,String> data;

  public Message(String msg) {
    message = msg;
    data = new HashMap<>();
  }

  public Message() {

  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public void set(String key, String value) {
    data.put(key,value);
  }

  @Override
  public String get(String key) {
    return data.get(key);
  }

  @Override
  public String getDestinationAddress() {
    return null;
  }

  @Override
  public IDataUnit getPayload() {
    return null;
  }

  @Override
  public String getSourceAddress() {
    return null;
  }
}
