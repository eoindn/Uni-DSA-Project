package PERDS.src.main.java.uk.ac.stmarys.perds.network;

import PERDS.src.main.java.uk.ac.stmarys.perds.core.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmergencyNetwork {


    private Map<String, List<String>> connections;
    private Map<String, Location> locations;

    public EmergencyNetwork() {
        this.locations = new HashMap<>();
        this.connections = new HashMap<>();
    }

    public void addLocation(Location location) {
        this.locations.put(location.getId(), location);
        this.connections.put(location.getId(), new ArrayList<>());
        System.out.println("Added: " + location.getName());
    }

    public List<String> getConnections(String locationId) {
        return this.connections.get(locationId);
    }

    public Location getLocation(String id) {
        return this.locations.get(id);
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void addConnection(String location1Id, String location2Id) {
        this.connections.get(location1Id).add(location2Id);
        this.connections.get(location2Id).add(location1Id);

        System.out.println("Connected to " + location1Id + " <-> " + location2Id);


    }


    public void printAllLocations() {
        System.out.println("\n=== All Locations in Network ===");
        for (Location loc : locations.values()) {
            System.out.println("  • " + loc.getName() + " (" + loc.getId() + ")");
        }
        System.out.println("Total: " + locations.size() + " locations\n");
    }
}