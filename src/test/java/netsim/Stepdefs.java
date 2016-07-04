package netsim;

import java.util.ArrayList;

import org.netsim.networking.DaggerNetworkingComponent;
import org.netsim.networking.DeviceModule;
import org.netsim.networking.NetworkingComponent;
import org.netsim.networking.device.ADevice;
import org.netsim.networking.hardware.AHardwareInterface;
import org.netsim.networking.protocol.IFrame;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class Stepdefs {

  private static NetworkingComponent deviceComponent = 
    DaggerNetworkingComponent.builder().deviceModule(new DeviceModule()).build();
  private static ADevice<? extends IFrame> accessPoint;
  private static AHardwareInterface<? extends IFrame> currentInterface = null;
  
  @Given("^An access point has at least one wlan interface working\\.$")
  public void an_access_point_has_at_least_one_wlan_interface_working() throws Throwable {
    accessPoint = deviceComponent.provideAccessPoint();
    ArrayList<AHardwareInterface<? extends IFrame>> interfaces; 
    interfaces = accessPoint.listWirelessInterfaces();

    for (AHardwareInterface<? extends IFrame> wlan: interfaces) {
      if (wlan.isUp()) {
        currentInterface = wlan;
        return;
      }
    }
    
    throw new PendingException();

  }

  @Given("^An access point has not reached it's user limit\\.$")
  public void an_access_point_has_not_reached_it_s_user_limit() throws Throwable {
    if (currentInterface.isLinkFull())
      throw new PendingException();
    return;
  }

  @Given("^A mobile node gets in the access point's coverage area\\.$")
  public void a_mobile_node_gets_in_the_access_point_s_coverage_area() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
       
  }

  @Given("^The mobile node sends a connection request\\.$")
  public void the_mobile_node_sends_a_connection_request() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
       
  }

  @Given("^The access point acknowledges the request\\.$")
  public void the_access_point_acknowledges_the_request() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
       
  }

  @Given("^The mobile node agrees on the connection\\.$")
  public void the_mobile_node_agrees_on_the_connection() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
       
  }

  @Given("^MobileNodeA is connected to an access point through its wlan interface\\.$")
  public void mobilenodea_is_connected_to_an_access_point_through_its_wlan_interface() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
       
  }

  @Given("^MobileNodeB enters MobileNodeA's coverage area\\.$")
  public void mobilenodeb_enters_MobileNodeA_s_coverage_area() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
       
  }

  @Given("^MobileNodeB asks MobileNodeB to create a connection\\.$")
  public void mobilenodeb_asks_MobileNodeB_to_create_a_connection() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
       
  }

  @Given("^MobileNodeA acknowledges the request\\.$")
  public void mobilenodea_acknowledges_the_request() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
       
  }

  @Given("^MobileNodeB agrees on the connection\\.$")
  public void mobilenodeb_agrees_on_the_connection() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^A plane has a length and a width\\.$")
  public void a_plane_has_a_length_and_a_width() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^Node exist in a two-dimensional plane, it means each node has a pair of coordinades \\(e\\.g\\.\\[x,y\\]\\)\\.$")
  public void node_exist_in_a_two_dimensional_plane_it_means_each_node_has_a_pair_of_coordinades_e_g_x_y() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^Node walks in a random direction for a random distance, without getting out of the plane's boundaries\\.$")
  public void node_walks_in_a_random_direction_for_a_random_distance_without_getting_out_of_the_plane_s_boundaries() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }
}
