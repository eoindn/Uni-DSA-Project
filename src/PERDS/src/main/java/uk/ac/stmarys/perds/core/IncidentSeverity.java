package PERDS.src.main.java.uk.ac.stmarys.perds.core;



public enum IncidentSeverity {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    CRITICAL(4);

    private final int priority;

    IncidentSeverity(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}