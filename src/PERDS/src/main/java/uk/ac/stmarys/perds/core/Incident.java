package PERDS.src.main.java.uk.ac.stmarys.perds.core;


import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;


public class Incident {

    private String id;
    private String description;
    private boolean resolved;
    private Location location;
    private IncidentSeverity incidentSeverity;
    private LocalDateTime localDateTime;


    public Incident(String id, String description, boolean resolved, Location location, IncidentSeverity incidentSeverity) {
        this.id = id;
        this.description = description;
        this.resolved = resolved;
        this.location = location;
        this.incidentSeverity = incidentSeverity;

    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public Location getLocation() { return location;}
    public IncidentSeverity getSeverity() { return incidentSeverity; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }


}