package PERDS.src.main.java.uk.ac.stmarys.perds.scheduling;

import PERDS.src.main.java.uk.ac.stmarys.perds.allocation.BasicAllocator;
import PERDS.src.main.java.uk.ac.stmarys.perds.core.Incident;
import PERDS.src.main.java.uk.ac.stmarys.perds.core.ResponseUnit;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.EmergencyNetwork;

public class Dispatcher {


    private EmergencyNetwork network;
    private BasicAllocator allocator;


    public Dispatcher(EmergencyNetwork network, BasicAllocator allocator) {
        this.network = network;
        this.allocator = allocator;
    }

    public void processQueue(){
        IncidentQueue queue = network.getIncidentQueue();
        int handled = 0;
        int failed = 0;

        while(queue.hasIncidents()){
            Incident next = queue.getNext();
            ResponseUnit unit = allocator.findNearestUnit(next);

            if(unit != null){
                dispatchUnit(unit,next);
                handled ++;
            }else{
                System.out.println("No unit was found , choppa on da way");
                failed++;
            }
        }
    }

    public void dispatchUnit(ResponseUnit unit, Incident incident){
        double distance = network.calculateDistance(unit.getLocation(), incident.getLocation());
        System.out.println("Dispatching " + unit.getType() + " coming from " + unit.getLocation().getName() +
                " arriving at " + incident.getLocation() + " in approximately " + network.getConnectionTme(unit.getLocation().getId(),incident.getLocation().getId()) + " distance is " +
                distance);
        unit.setAvailable(false);
    }
    //simulate freeing up a unit used in the incident
    public void resolveIncident(Incident incident, ResponseUnit unit){

        incident.setResolved(true);
        unit.setAvailable(true);
        System.out.println("Incident resolved: " + incident.getDescription());
        System.out.println("Unit: " + unit.getId() + " is now available");
    }
}
