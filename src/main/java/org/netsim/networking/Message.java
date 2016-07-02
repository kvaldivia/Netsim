package org.netsim.networking;

import java.util.HashMap;

public class Message implements IDataUnit{
  private String message;
  private HashMap<String,String> data;

  public Message(String msg) {
    message = msg;
    data = new HashMap<>();
  }

  public Message() {

  }

  public String getMessage() {
    return message;
  }

  public void set(String key, String value) {
    data.put(key,value);
  }

  public String get(String key) {
    return data.get(key);
  }

  public String getDestinationAddress() {
    return null;
  }

  public IDataUnit getPayload() {
    return null;
  }

  public String getSourceAddress() {
    return null;
  }
}
