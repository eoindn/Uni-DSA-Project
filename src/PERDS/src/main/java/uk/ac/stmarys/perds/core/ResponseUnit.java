package PERDS.src.main.java.uk.ac.stmarys.perds.core;


class ResponseUnit{


    private String id;
    private Location location;
    private String type;
    private boolean available;

    public ResponseUnit(String id, Location location, String type, boolean available) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}