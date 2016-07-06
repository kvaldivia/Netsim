package org.netsim.networking.protocol;

import java.util.ArrayList;

/* This class defines the behavior for all the protocols in netsim.
 * Considering the fact that a protocol is an object that receives a
 * IDataUnit object and wraps it with it's own IDataUnit.
 *
 * T1: Upper protocol's IDataUnit object
 * T2: Lower protocol's IDataUnit object
 */

public interface IProtocol<T1 extends IDataUnit,T2 extends IDataUnit> extends Runnable {
  T2 wrap(T1 payload, String dest, String consumer);

  T2 wrap(T1 payload, String source, String dest, String consumer);

  String getAddress();

  void addConsumer(IProtocol<? extends IDataUnit,T1> consumer);

  void addSource(IProtocol<T2,? extends IDataUnit> source);

  void send(T1 payload, String dest, String consumer);

  ArrayList<IProtocol<? extends IDataUnit,T1>> listConsumers();

  ArrayList<IProtocol<T2,? extends IDataUnit>> listSources();

  void setThread(Thread t);

  void run();

  void receive(T2 msg, String through);

}
