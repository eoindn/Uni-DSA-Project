package PERDS.src.main.java.uk.ac.stmarys.perds.core;


import PERDS.src.main.java.uk.ac.stmarys.perds.allocation.BasicAllocator;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.EmergencyNetwork;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.NetworkManager;
import PERDS.src.main.java.uk.ac.stmarys.perds.scheduling.IncidentQueue;

import java.util.List;

public class Main {

    //Test run to make sure everything is running smoothly
    public static void main(String[] args) {




        EmergencyNetwork network = new EmergencyNetwork();
        BasicAllocator allocator = new BasicAllocator(network);
        NetworkManager manager = new NetworkManager(network);
        IncidentQueue incidentQueue = new IncidentQueue();



        // Create locations
        Location london = new Location("LOC001", "London", 51.5074, -0.1278);
        Location manchester = new Location("LOC002", "Manchester", 53.4808, -2.2426);
        Location birmingham = new Location("LOC003", "Birmingham", 52.4862, -1.8904);
        Location liverpool = new Location("LOC004", "Liverpool", 53.4084, -2.9916);

        //------------------------------------------------------------//

        // Add locations to network
        System.out.println("Adding locations...");
        network.addLocation(london);
        network.addLocation(manchester);
        network.addLocation(birmingham);
        network.addLocation(liverpool);

        //------------------------------------------------------------//

        System.out.println("\nCreating connections...");
        network.addConnection("LOC001", "LOC002",330,260);  // London ↔ Manchester
        network.addConnection("LOC001", "LOC003",163,120);  // London ↔ Birmingham
        network.addConnection("LOC002", "LOC003",135,90);  // Manchester ↔ Birmingham
        network.addConnection("LOC002", "LOC004",56,45);
        System.out.println("\n");// Manchester ↔ Liverpool
        System.out.println();

        //------------------------------------------------------------//
        //unit type like vehicles such as fire engines and what not
        ResponseUnit ambulance = new ResponseUnit("UNIT001", london, "AMBULANCE", true);
        ResponseUnit ambulance2 = new ResponseUnit("UNIT002", manchester, "AMBULANCE", true);  // Change to UNIT002
        ResponseUnit policeCar = new ResponseUnit("UNIT003", birmingham, "POLICE", true);      // Change to UNIT003
        ResponseUnit policeCar2 = new ResponseUnit("UNIT004", liverpool, "POLICE", true);      // Change to UNIT004

        //incident
        Incident fire = new Incident("INC001", "Arson", false, london, IncidentSeverity.HIGH);  // Changed ID to INC001

        //Location
        Location retrieved = network.getLocation("LOC001");  // Changed to valid location ID

        //------------------------------------------------------------//


        System.out.println("Retrieved location: " + retrieved.getName());
        System.out.println("Getting connections for london");

        List<EmergencyNetwork.Connection> londonConnections = network.getConnections("LOC001");
        System.out.println("London connects to: " + londonConnections.toString());

        System.out.println("Unit: " + ambulance.getType());
        System.out.println("Incident at: " + fire.getLocation().getName());
        System.out.println("Severity: " + fire.getSeverity());
        System.out.println("Got location: " + retrieved.getName() + "\n");


        System.out.println("Checking response units...");

        network.addUnit(ambulance);
        network.addUnit(policeCar);
        network.addUnit(policeCar2);
        network.addUnit(ambulance2);

        ResponseUnit dispatched = allocator.findNearestUnit(fire);

        String dispatchedLocationId = dispatched.getLocation().getId();
        String incidentLocationId = fire.getLocation().getId();

        // After creating your network and connections...

        System.out.println("\n=== Testing Dynamic Updates ===");



// Show original connection time

        double originalTime = network.getConnectionTme("LOC001", "LOC002");
        System.out.println("Original London-Manchester time: " + originalTime + " minutes");

// Simulate traffic congestion - double the travel time!
        manager.updateConnectionTime("LOC001", "LOC002", 480);  // Was 240, now 480

// Check the updated time
        double newTime = network.getConnectionTme("LOC001", "LOC002");
        System.out.println("After traffic update: " + newTime + " minutes");

// Simulate road closure - remove a connection
        System.out.println("\nSimulating road closure...");
        manager.removeConnection("LOC002", "LOC004");  // Close Manchester-Liverpool road

        Incident incident1 = new Incident("INC001", "Minor injury", false, london, IncidentSeverity.LOW);
        Incident incident2 = new Incident("INC002", "House fire", false, manchester, IncidentSeverity.HIGH);
        Incident incident3 = new Incident("INC003", "Heart attack", false, birmingham, IncidentSeverity.CRITICAL);
        Incident incident4 = new Incident("INC004", "Car accident", false, liverpool, IncidentSeverity.MEDIUM);

// Add them in random order
        incidentQueue.addIncident(incident1);  // LOW
        incidentQueue.addIncident(incident2);  // HIGH
        incidentQueue.addIncident(incident3);  // CRITICAL
        incidentQueue.addIncident(incident4);  // MEDIUM

        System.out.println("\nQueue size: " + incidentQueue.Size());

// Process them - they should come out in priority order!
        System.out.println("\n=== Processing Incidents by Priority ===");
        while (incidentQueue.hasIncidents()) {
            Incident next = incidentQueue.getNext();
            System.out.println("  → " + next.getDescription() + " at " + next.getLocation().getName());
        }






// Try to get time for closed road
        double closedTime = network.getConnectionTme("LOC002", "LOC004");
        System.out.println("Manchester-Liverpool time: " + (closedTime == -1 ? "ROAD CLOSED" : closedTime + " min"));


        if (dispatched != null) {
            System.out.println("Incident: " + fire.getDescription() + " at " + fire.getLocation().getName());
            System.out.println("Dispatching: " + dispatched.getId() + " (" + dispatched.getType() + ")");
            System.out.println("From: " + dispatched.getLocation().getName());
            double time = network.getConnectionTme(dispatchedLocationId, incidentLocationId);
            if (time != -1) {  // Changed from != 0 to != -1 (getConnectionTime returns -1 if not connected)
                System.out.println("Travel time from " + dispatched.getLocation().getName() +
                        " to " + fire.getLocation().getName() + ": " + time + " minutes");
            } else {
                System.out.println("Locations not directly connected");
            }


            double distance = network.calculateDistance(dispatched.getLocation(), fire.getLocation());
            System.out.println("Distance: " + distance + " km");

            // Mark unit as unavailable (cos it's been dispatched)
            dispatched.setAvailable(false);
        } else {
            System.out.println("No available units!");
        }
        System.out.println( );









    }
}