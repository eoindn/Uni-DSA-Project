package PERDS.src.main.java.uk.ac.stmarys.perds.network;

import PERDS.src.main.java.uk.ac.stmarys.perds.core.Location;
import PERDS.src.main.java.uk.ac.stmarys.perds.core.ResponseUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EmergencyNetwork {


    private Map<String, List<String>> connections;
    private Map<String, Location> locations;
    private List<ResponseUnit> Responseunits;


    public EmergencyNetwork() {
        this.locations = new HashMap<>();
        this.connections = new HashMap<>();
        this.Responseunits = new ArrayList<>();
    }

    public void addUnit(ResponseUnit unit) {
        this.Responseunits.add(unit);
        System.out.println("Registered unit: " + unit.getId() + " " + unit.getType());

    }

    public List<ResponseUnit> getUnits() {
        return this.Responseunits;
    }

    public void addLocation(Location location) {
        this.locations.put(location.getId(), location);
        this.connections.put(location.getId(), new ArrayList<>());
        System.out.println("Added: " + location.getName());
    }

    //------------------------------------------------------------//

    public List<String> getConnections(String locationId) {
        return this.connections.get(locationId);
    }

    //------------------------------------------------------------//

    public Location getLocation(String id) {
        return this.locations.get(id);
    }

    //------------------------------------------------------------//

    public Map<String, Location> getLocations() {
        return locations;
    }

    //------------------------------------------------------------//

    public double calculateDistance(Location loc1, Location loc2) {

        double lat1 = loc1.getLatitude();
        double lon1 = loc1.getLongitude();
        double lat2 = loc2.getLatitude();
        double lon2 = loc2.getLongitude();

        double latDiff = lat2 - lat1;
        double lonDiff = lon2 - lon1;

        double distance = Math.sqrt(latDiff * latDiff + lonDiff * lonDiff) * 111;

        return distance;
    }





    //------------------------------------------------------------//

    public void addConnection(String location1Id, String location2Id) {
        this.connections.get(location1Id).add(location2Id);
        this.connections.get(location2Id).add(location1Id);

        System.out.println("Connected to " + location1Id + " <-> " + location2Id);


    }

    //------------------------------------------------------------//





    public void printAllLocations() {
        System.out.println("\n=== All Locations in Network ===");
        for (Location loc : locations.values()) {
            System.out.println("  • " + loc.getName() + " (" + loc.getId() + ")");
        }
        System.out.println("Total: " + locations.size() + " locations\n");
    }
}