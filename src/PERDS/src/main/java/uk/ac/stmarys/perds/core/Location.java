package PERDS.src.main.java.uk.ac.stmarys.perds.core;

public class Location{


    //to print the entire dictionary we make in emergency network class
    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + latitude +
                ", lon=" + longitude +
                '}';
    }

    private String name;
    private String id;
    private double latitude;
    private double longitude;

    public Location(String id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {return name;}
    public String getId() {return id;}
    public double getLatitude() {return latitude;}
    public double getLongitude() {return longitude;}

    public void setName(String name) {this.name = name;}
    public void setId(String id) {this.id = id;}
    public void setLatitude(double latitude) {this.latitude = latitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}
}