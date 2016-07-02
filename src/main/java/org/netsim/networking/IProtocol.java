package org.netsim.networking;

import java.lang.Thread;
import java.util.ArrayList;

/* This class defines the behavior for all the protocols in netsim.
 * Considering the fact that a protocol is an object that receives a
 * IDataUnit object and wraps it with it's own IDataUnit.
 *
 * T1: Upper protocol's IDataUnit object
 * T2: Lower protocol's IDataUnit object
 */

public interface IProtocol<T1 extends IDataUnit,T2 extends IDataUnit> extends Runnable {
  public T2 wrap(T1 payload, String dest, String consumer);

  public T2 wrap(T1 payload, String source, String dest, String consumer);

  public T1 unwrap(T2 pdu);

  public String getAddress();

  public void addConsumer(IProtocol<? extends IDataUnit,T1> consumer);

  public void addSource(IProtocol<T2,? extends IDataUnit> source);

  public void send(T1 payload, String dest, String consumer);

  public void send(T1 payload, String source, String dest, String consumer);

  public void receive(T2 message, IProtocol<T2,? extends IDataUnit> source);

  public ArrayList<IProtocol<? extends IDataUnit,T1>> listConsumers();

  public ArrayList<IProtocol<T2,? extends IDataUnit>> listSources();

  public void setThread(Thread t);

  public void run();
}
