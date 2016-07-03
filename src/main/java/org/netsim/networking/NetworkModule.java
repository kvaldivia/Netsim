package org.netsim.networking;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
  @Provides
  @Singleton
  @Named("middle_size_network")
  public Network provideNetwork() {
    return new Network();
  }
}
