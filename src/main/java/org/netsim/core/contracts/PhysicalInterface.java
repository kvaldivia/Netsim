package org.netsim.core.contracts;

public abstract class PhysicalInterface {
    private String id;
    private NetworkInterface ip;
    private DataLinkInterface port;
    public abstract String getId();
    public abstract String getIp();
    public abstract String getMac();
}
