Feature: Node networking simulation
    Scenario: A mobile node connects to an access point.
        * An access point has at least one wlan interface working.
        * An access point has not reached it's user limit.
        * A mobile node gets in the access point's coverage area.
        * The mobile node sends a connection request.
        * The access point acknowledges the request.
        * The mobile node agrees on the connection.

    Scenario: MobileNodeA turns into an access point, and allows MobileNodeB to connect.

        MobileNodeA has the capability to turn into an access point and grants
        MobileNodeB access to the wireless network.

        * MobileNodeA is connected to an access point through its wlan interface.
        * MobileNodeB enters MobileNodeA's coverage area.
        * MobileNodeB asks MobileNodeB to create a connection.
        * MobileNodeA acknowledges the request.
        * MobileNodeB agrees on the connection.
