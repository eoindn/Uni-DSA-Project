package PERDS.src.main.java.uk.ac.stmarys.perds.allocation;
import PERDS.src.main.java.uk.ac.stmarys.perds.core.*;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.EmergencyNetwork;

import java.util.List;


public class BasicAllocator {

    /* TODO: Basic allocation algorithm - finds nearest available unit
      Time Complexity: O(n) where n = number of response units
      Basic allocation algorithm - finds nearest available unit
      Time Complexity: O(n) where n = number of response units
     */

    private EmergencyNetwork network;

    public BasicAllocator(EmergencyNetwork network) {
        this.network= network;
    }


    public ResponseUnit findNearestUnit(Incident incident) {

        ResponseUnit nearestUnit= null;
        double shortestDistance= Double.MAX_VALUE;


        List<ResponseUnit> units = network.getUnits();

        for(ResponseUnit unit : units) {
            if(unit.isAvailable()){
                double distance = network.calculateDistance(unit.getLocation(), incident.getLocation());
                if(distance < shortestDistance) {
                    shortestDistance= distance;
                    nearestUnit= unit;
                }

            }
        }
        return nearestUnit;
    }






}