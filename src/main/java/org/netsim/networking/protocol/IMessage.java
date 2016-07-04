package org.netsim.networking.protocol;

public interface IMessage extends IDataUnit {

	String getMessage();

	void set(String key, String value);

	String get(String key);

}
