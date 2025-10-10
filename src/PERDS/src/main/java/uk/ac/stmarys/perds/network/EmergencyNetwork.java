package PERDS.src.main.java.uk.ac.stmarys.perds.network;

import PERDS.src.main.java.uk.ac.stmarys.perds.core.Incident;
import PERDS.src.main.java.uk.ac.stmarys.perds.core.Location;
import PERDS.src.main.java.uk.ac.stmarys.perds.core.ResponseUnit;
import PERDS.src.main.java.uk.ac.stmarys.perds.scheduling.IncidentQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EmergencyNetwork {


    private Map<String, List<Connection>> connections;; //changed map to take Connection object from new class
    private Map<String, Location> locations;
    private List<ResponseUnit> Responseunits;
    private IncidentQueue incidentQueue;


    public EmergencyNetwork() {
        this.locations = new HashMap<>();
        this.connections = new HashMap<>();
        this.Responseunits = new ArrayList<>();
        this.incidentQueue = new IncidentQueue();
    }

    public IncidentQueue getIncidentQueue() {
        return incidentQueue;
    }

    public void reportIncident(Incident incident){
        incidentQueue.addIncident(incident);
        System.out.println("New incident for " + incident.getDescription() + " at " + incident.getLocation() + " with severity "
        + incident.getSeverity());
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

    public List<Connection> getConnections(String locationId) {
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


    //inner class to represent a connection between locations



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



    public void printAllLocations() {  // ← MOVE THIS INSIDE THE CLASS
        System.out.println("\n=== All Locations in Network ===");
        for (Location loc : locations.values()) {
            System.out.println("  • " + loc.getName() + " (" + loc.getId() + ")");
        }
        System.out.println("Total: " + locations.size() + " locations\n");
    }





    //------------------------------------------------------------//

    public void addConnection(String location1Id, String location2Id, double distance, double travelTime) {
        // create connections in both directions
        this.connections.get(location1Id).add(new Connection(location2Id, distance, travelTime));
        this.connections.get(location2Id).add(new Connection(location1Id, distance, travelTime));

        System.out.println("Connected: " + location1Id + " <-> " + location2Id +
                " (" + distance + "km, " + travelTime + "min)");
    }

    public double getConnectionTme(String location1Id, String location2Id) {
        List<Connection> connectionsFromLocation1 = connections.get(location1Id);

        for(Connection conn : connectionsFromLocation1) {
            if(conn.getDestinationId().equals(location2Id)) {
                return conn.getDistance();
            }
        }
        return 0;
    }

    public static class Connection {
        private String destinationId;
        private double distance;      // in kilometers
        private double travelTime;    // in minutes

        public Connection(String destinationId, double distance, double travelTime) {
            this.destinationId = destinationId;
            this.distance = distance;
            this.travelTime = travelTime;
        }

        // Getters
        public String getDestinationId() { return destinationId; }
        public double getDistance() { return distance; }
        public double getTravelTime() { return travelTime; }

        // Setters for dynamic updates
        public void setDistance(double distance) { this.distance = distance; }
        public void setTravelTime(double travelTime) { this.travelTime = travelTime; }


    }


}

//------------------------------------------------------------//






