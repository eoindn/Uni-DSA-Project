package PERDS.src.main.java.uk.ac.stmarys.perds.core;


import PERDS.src.main.java.uk.ac.stmarys.perds.network.EmergencyNetwork;

import java.util.List;
import java.util.Map;

public class Main {

    //Test run to make sure everything is running smoothly
    public static void main(String[] args) {




        EmergencyNetwork network = new EmergencyNetwork();

        // Create locations
        Location london = new Location("LOC001", "London", 51.5074, -0.1278);
        Location manchester = new Location("LOC002", "Manchester", 53.4808, -2.2426);
        Location birmingham = new Location("LOC003", "Birmingham", 52.4862, -1.8904);
        Location liverpool = new Location("LOC004", "Liverpool", 53.4084, -2.9916);

        // Add locations to network
        System.out.println("Adding locations...");
        network.addLocation(london);
        network.addLocation(manchester);
        network.addLocation(birmingham);
        network.addLocation(liverpool);

        System.out.println("\nCreating connections...");
        network.addConnection("LOC001", "LOC002");  // London ↔ Manchester
        network.addConnection("LOC001", "LOC003");  // London ↔ Birmingham
        network.addConnection("LOC002", "LOC003");  // Manchester ↔ Birmingham
        network.addConnection("LOC002", "LOC004");  // Manchester ↔ Liverpool


        ResponseUnit ambulance = new ResponseUnit("UNIT001", london, "AMBULANCE", true);
        Incident fire = new Incident("INC001", "Arson", false, london, IncidentSeverity.HIGH);  // Changed ID to INC001
        Location retrieved = network.getLocation("LOC001");  // Changed to valid location ID

        System.out.println("Retrieved location: " + retrieved.getName());  // Now works!
        System.out.println("Getting connections for london");

        List<String> londonConnections = network.getConnections("LOC001");
        System.out.println("London connects to: " + londonConnections);

        System.out.println("Unit: " + ambulance.getType());
        System.out.println("Incident at: " + fire.getLocation().getName());
        System.out.println("Severity: " + fire.getSeverity());
        System.out.println("Got location: " + retrieved.getName());  // Now works!


    }
}