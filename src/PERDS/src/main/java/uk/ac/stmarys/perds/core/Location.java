package PERDS.src.main.java.uk.ac.stmarys.perds.core;

class Location{

    private String name;
    private int id;
    private double latitude;
    private double longitude;


    public String getName() {return name;}
    public int getId() {return id;}
    public double getLatitude() {return latitude;}
    public double getLongitude() {return longitude;}

    public void setName(String name) {this.name = name;}
    public void setId(int id) {this.id = id;}
    public void setLatitude(double latitude) {this.latitude = latitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}
}