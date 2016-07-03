package org.netsim.networking;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

public class Network {
  private HashMap<String, ADevice<? extends IDataUnit>> nodes;
  private int apCounter;
  private int mobCounter;
  private static final String STR_ACCESSPOINT_PREFFIX = "AP-";
  private static final String STR_MOBILENODE_PREFFIX = "MOB-";

  @Inject
  public Network() {
    nodes = new HashMap<>(50);
    apCounter = 0;
    mobCounter = 0;
  }

  public void addAccessPoint(ADevice<? extends IFrame> ap) {
    String name = STR_ACCESSPOINT_PREFFIX + apCounter;
    apCounter++;
    addNode(name, ap);
  }

  public void addMobileNode(AccessPoint mob) {
    String name = STR_MOBILENODE_PREFFIX + mobCounter;
    mobCounter++;
    addNode(name, mob);
  }

  public ArrayList<ADevice<? extends IDataUnit>> listNodes() {
    ArrayList<ADevice<? extends IDataUnit>> list =
      new ArrayList<>(nodes.values());
    return list;
  }

  private <D extends ADevice<? extends IDataUnit>>
  void addNode(String name, D node) {
    nodes.put(name, node);
  }
}
