package PERDS.src.main.java.uk.ac.stmarys.perds.core;


import java.time.LocalDateTime;


class Incident {

    private String id;
    private String description;
    private boolean resolved;
    private Location location;
    private IncidentSeverity incidentSeverity;
    private LocalDateTime localDateTime;


    public String getId() { return id; }
    public Location getLocation() { return location;}
    public IncidentSeverity getSeverity() { return incidentSeverity; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }


}