package org.netsim.device;

import dagger.Component;

@Component(modules = {DeviceModule.class})
public interface DeviceComponent {
  AccessPoint provideAccessPoint();
}
