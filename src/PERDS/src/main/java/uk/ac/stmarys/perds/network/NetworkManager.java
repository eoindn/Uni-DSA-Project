package PERDS.src.main.java.uk.ac.stmarys.perds.network;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.EmergencyNetwork.Connection;


import PERDS.src.main.java.uk.ac.stmarys.perds.core.Location;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.EmergencyNetwork;

import java.util.List;

/**
 * Manages dynamic updates to the emergency network
 * Handles adding/removing locations and updating connection weights
 */


public class NetworkManager {

    private EmergencyNetwork network;

    public NetworkManager(EmergencyNetwork network) {
        this.network = network;
    }

    public void removeConnection(String location1Id, String location2Id) {

        List<Connection> connectionsFrom1 = network.getConnections(location1Id);

        connectionsFrom1.removeIf(conn -> conn.getDestinationId().equals(location2Id));


        List<Connection> connectionsFrom2 = network.getConnections(location2Id);
        connectionsFrom2.removeIf(conn -> conn.getDestinationId().equals(location1Id));

        System.out.println("Removed connection: " + location1Id + " <-> " + location2Id);
    }

    /**
     * Removes a connection between two locations (e.g., road closure)
     * Time Complexity: O(degree) where degree = connections from location
     */


    /**
     * Updates travel time for a connection (eg traffic congestion)
     * Time Complexity: O(degree)
     */
    public void updateConnectionTime(String location1Id, String location2Id, double newTime) {
        List<Connection> connectionsFrom1 = network.getConnections(location1Id);
        for(Connection conn : connectionsFrom1) {
            if(conn.getDestinationId().equals(location2Id)) {
                conn.setTravelTime(newTime);
                break;
            }

        }
        List<Connection> connectionsFrom2 = network.getConnections(location2Id);

        for(Connection conn : connectionsFrom2){
            if(conn.getDestinationId().equals(location1Id)) {
                conn.setTravelTime(newTime);
                break;
            }
        }
        System.out.println("Updated travel time: " + location1Id + " <-> " + location2Id + " to " + newTime + " minutes");


    }

    /**
     * Updates distance for a connection (eg detour required)
     * Time Complexity: O(degree)
     */
    public void updateConnectionDistance(String location1Id, String location2Id, double newDistance) {
        List<Connection> connectionsFrom1 = network.getConnections(location1Id);
        for(Connection conn : connectionsFrom1) {
            if(conn.getDestinationId().equals(location2Id)){
                conn.setDistance(newDistance);
                break;
            }
        }
        List<Connection> connectionsFrom2 = network.getConnections(location2Id);
        for(Connection conn : connectionsFrom2) {
            if(conn.getDestinationId().equals(location1Id)) {
                conn.setDistance(newDistance);
                break;
            }

        }
        System.out.println("New distance: " + location1Id + " <-> " + location2Id + " to " + newDistance + "km");


    }
}