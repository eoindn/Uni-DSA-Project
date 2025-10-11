package PERDS.src.main.java.uk.ac.stmarys.perds.core;

import PERDS.src.main.java.uk.ac.stmarys.perds.allocation.BasicAllocator;
import PERDS.src.main.java.uk.ac.stmarys.perds.allocation.OptimisedAllocator;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.EmergencyNetwork;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.NetworkManager;
import PERDS.src.main.java.uk.ac.stmarys.perds.scheduling.Dispatcher;

public class Main {

    public static void main(String[] args) {

        // Initialise core systems
        EmergencyNetwork network = new EmergencyNetwork();
        BasicAllocator allocator = new BasicAllocator(network);
        NetworkManager manager = new NetworkManager(network);
        Dispatcher dispatcher = new Dispatcher(network, allocator);

        // Setup network
        setupNetwork(network);

        // Register response units
        registerUnits(network);

        // Test dynamic updates
        testDynamicUpdates(network, manager);

        // Test concurrent incident management
        testIncidentManagement(network, dispatcher);

        testOptimisedAllocator(network);
    }

    /**
     * Setup the emergency network with locations and connections
     */
    private static void setupNetwork(EmergencyNetwork network) {
        System.out.println("=== SETTING UP EMERGENCY NETWORK ===\n");

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

        // Create connections (road network)
        System.out.println("\nCreating connections...");
        network.addConnection("LOC001", "LOC002", 330, 260);  // London ↔ Manchester
        network.addConnection("LOC001", "LOC003", 163, 120);  // London ↔ Birmingham
        network.addConnection("LOC002", "LOC003", 135, 90);   // Manchester ↔ Birmingham
        network.addConnection("LOC002", "LOC004", 56, 45);    // Manchester ↔ Liverpool

        System.out.println();
    }

    /**
     * Register response units at various locations
     */
    private static void registerUnits(EmergencyNetwork network) {
        System.out.println("=== REGISTERING RESPONSE UNITS ===\n");

        // Get locations for unit placement
        Location london = network.getLocation("LOC001");
        Location manchester = network.getLocation("LOC002");
        Location birmingham = network.getLocation("LOC003");
        Location liverpool = network.getLocation("LOC004");

        // Create and register units
        ResponseUnit ambulance1 = new ResponseUnit("UNIT001", london, "AMBULANCE", true);
        ResponseUnit ambulance2 = new ResponseUnit("UNIT002", manchester, "AMBULANCE", true);
        ResponseUnit police1 = new ResponseUnit("UNIT003", birmingham, "POLICE", true);
        ResponseUnit police2 = new ResponseUnit("UNIT004", liverpool, "POLICE", true);

        network.addUnit(ambulance1);
        network.addUnit(ambulance2);
        network.addUnit(police1);
        network.addUnit(police2);

        System.out.println();
    }

    /**
     * Test dynamic network updates (Phase 2)
     */
    private static void testDynamicUpdates(EmergencyNetwork network, NetworkManager manager) {
        System.out.println("=== TESTING DYNAMIC UPDATES ===\n");

        // Test connection time update
        double originalTime = network.getConnectionTme("LOC001", "LOC002");
        System.out.println("Original London-Manchester time: " + originalTime + " minutes");

        manager.updateConnectionTime("LOC001", "LOC002", 480);  // Simulate traffic

        double newTime = network.getConnectionTme("LOC001", "LOC002");
        System.out.println("After traffic congestion: " + newTime + " minutes");

        // Test connection removal
        System.out.println("\nSimulating road closure...");
        manager.removeConnection("LOC002", "LOC004");

        double closedTime = network.getConnectionTme("LOC002", "LOC004");
        System.out.println("Manchester-Liverpool: " + (closedTime == -1 ? "ROAD CLOSED ✅" : closedTime + " min"));

        // Restore connection for later tests
        network.addConnection("LOC002", "LOC004", 56, 45);
        System.out.println("Road reopened for testing\n");
    }

    /**
     * Test concurrent incident management with priority queue (Phase 2)
     */
    private static void testIncidentManagement(EmergencyNetwork network, Dispatcher dispatcher) {
        System.out.println("=== TESTING CONCURRENT INCIDENT MANAGEMENT ===\n");

        // Get locations
        Location london = network.getLocation("LOC001");
        Location manchester = network.getLocation("LOC002");
        Location birmingham = network.getLocation("LOC003");
        Location liverpool = network.getLocation("LOC004");

        // Report multiple incidents (different locations and severities)
        System.out.println("Reporting incidents...");
        network.reportIncident(new Incident("INC001", "Minor injury", false, liverpool, IncidentSeverity.LOW));
        network.reportIncident(new Incident("INC002", "House fire", false, birmingham, IncidentSeverity.HIGH));
        network.reportIncident(new Incident("INC003", "Heart attack", false, london, IncidentSeverity.CRITICAL));
        network.reportIncident(new Incident("INC004", "Car crash", false, manchester, IncidentSeverity.MEDIUM));

        System.out.println("\nIncidents in queue: " + network.getIncidentQueue().Size());
        System.out.println("Note: Should process in priority order (CRITICAL → HIGH → MEDIUM → LOW)\n");

        // Process all incidents
        dispatcher.processQueue();

        System.out.println("=== Testing Basic Reallocation ===\n");

        // Get first unit and incident (paired from dispatch)


        ResponseUnit busyUnit = network.getUnits().get(0);
        Incident resolvedIncident = new Incident("INC001", "Heart attack", true,
                network.getLocation("LOC001"), IncidentSeverity.CRITICAL);

        // complete the incident - frees up the unit
        dispatcher.resolveIncident(resolvedIncident, busyUnit);

        network.reportIncident(new Incident("INC005", "New fire", false,
                network.getLocation("LOC002"), IncidentSeverity.HIGH));

        System.out.println("Processing new incident with reallocated unit...");
        dispatcher.processQueue();

        printUnitStatus(network);
    }

    /**
     * Print status of all response units
     */
    private static void printUnitStatus(EmergencyNetwork network) {
        System.out.println("\n=== UNIT STATUS ===");
        for (ResponseUnit unit : network.getUnits()) {
            String status = unit.isAvailable() ? "AVAILABLE ✅" : "BUSY ❌";
            System.out.println(unit.getId() + " (" + unit.getType() + ") at " +
                    unit.getLocation().getName() + ": " + status);
        }
        System.out.println();
    }

    private static void testOptimisedAllocator(EmergencyNetwork network){
        System.out.println("=== TESTING OPTIMIZED ALLOCATION (MULTI-CRITERIA) ===\n");

        network.getUnits().clear();

        Location london = network.getLocation("LOC001");
        Location manchester = network.getLocation("LOC002");
        Location birmingham = network.getLocation("LOC003");

        // Add fresh available units
        network.addUnit(new ResponseUnit("UNIT005", manchester, "AMBULANCE", true));
        network.addUnit(new ResponseUnit("UNIT006", birmingham, "POLICE", true));
        network.addUnit(new ResponseUnit("UNIT007", london, "FIRE_TRUCK", true));

        OptimisedAllocator goodAllocator = new OptimisedAllocator(network);
        Incident incident1 = new Incident("123","Shot in heart",false,network.getLocation("LOC001"),
                IncidentSeverity.HIGH);

        System.out.println("Incident: " + incident1.getDescription());
        System.out.println("Location: " + incident1.getLocation().getName());
        System.out.println("Severity: " + incident1.getSeverity() + "\n");

        ResponseUnit best = goodAllocator.findBestUnit(incident1);


        if (best != null) {
            System.out.println("\nBest match: " + best.getType() + " from " +
                    best.getLocation().getName());
            System.out.println("(Should prefer AMBULANCE for medical emergency)");
        }





    }
}