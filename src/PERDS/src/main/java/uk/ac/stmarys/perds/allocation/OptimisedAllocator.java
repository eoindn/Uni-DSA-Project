package PERDS.src.main.java.uk.ac.stmarys.perds.allocation;
import PERDS.src.main.java.uk.ac.stmarys.perds.core.*;
import PERDS.src.main.java.uk.ac.stmarys.perds.network.EmergencyNetwork;


public class OptimisedAllocator{

    private EmergencyNetwork network;

    public OptimisedAllocator(EmergencyNetwork network) {
        this.network = network;
    }

    /**
     * Advanced allocation using multiple criteria.
     * Considers distance, unit type, severity, and travel time.
     * Time Complexity: O(n) where n = number of units.
     **/

    private static final double DISTANCE_WEIGHT = 0.4;
    private static final double TYPE_MATCH_WEIGHT = 0.3;
    private static final double SEVERITY_WEIGHT = 0.2;
    private static final double TIME_WEIGHT = 0.1;


    public ResponseUnit findBestUnit(Incident incident){

        ResponseUnit bestUnit = null;
        double bestScore = Double.MAX_VALUE;

        for(ResponseUnit unit : network.getUnits()){
            if(!unit.isAvailable()){continue;}
            double score = calculateScore(unit,incident);

            if(score < bestScore){
                bestScore = score;
                bestUnit = unit;
            }
        }  if (bestUnit != null) {
            System.out.println("Selected: " + bestUnit.getId() + " with score: " +
                    String.format("%.2f", bestScore));
        }

        return bestUnit;
    }

    private double calculateScore(ResponseUnit unit, Incident incident) {

        double typeScore = calculateTypeScore(unit,incident);
        double distanceScore = calculateDistanceScore(unit,incident);
        double severityScore = calculateSeverityScore(incident);
        double timeScore = calculateTimeScore(unit,incident);

        return (typeScore * TYPE_MATCH_WEIGHT + distanceScore * DISTANCE_WEIGHT
         + severityScore * SEVERITY_WEIGHT + timeScore * TIME_WEIGHT );



    }

    private double calculateTypeScore(ResponseUnit unit, Incident incident){
        String unitType = unit.getType().toUpperCase();
        String incidentDesc = incident.getDescription().toUpperCase();


        if (incidentDesc.contains("FIRE") || incidentDesc.contains("SMOKE")) {
            return unitType.contains("FIRE") ? 0 : 50;
        }

        if (incidentDesc.contains("MEDICAL") || incidentDesc.contains("INJURY") ||
                incidentDesc.contains("HEART") || incidentDesc.contains("AMBULANCE")) {
            return unitType.contains("AMBULANCE") ? 0 : 50;
        }

        if (incidentDesc.contains("CRIME") || incidentDesc.contains("ASSAULT") ||
                incidentDesc.contains("THEFT")) {
            return unitType.contains("POLICE") ? 0 : 50;
        }

        // No clear match so will return a neutral score
        return 25;


    }
    private double calculateTimeScore(ResponseUnit unit, Incident incident) {
        double time = network.getConnectionTme(unit.getLocation().getId(),incident.getLocation().getId());
        if(time == -1){
            return 75.0;
        }else{
            return Math.min(time / 3 ,100);
        }

    }


    private double calculateSeverityScore(Incident incident){
        return 100 - (incident.getSeverity().getPriority() * 25);
    }


    private double calculateDistanceScore(ResponseUnit unit, Incident incident){

        double distance = network.calculateDistance(unit.getLocation(),incident.getLocation());
        return Math.min(distance / 5.0,100);}

}