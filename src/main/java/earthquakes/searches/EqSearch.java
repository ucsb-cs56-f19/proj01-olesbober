package earthquakes.searches;

public class EqSearch {
    private int distance, minmag;
    private long lat, lon;
    private String location;

    public EqSearch() {
        this.distance = 0;
        this.minmag = 0;
        this.lat = 0;
        this.lon = 0;
        this.location = "";
    }

    public int getDistance() { return this.distance; }

    public int getMinmag() { return this.minmag; }

    public long getLat() { return this.lat; }

    public long getLon() { return this.lon; }

    public String getLocation() { return this.location; }

    public void setDistance(int distance) { this.distance = distance; }

    public void setMinmag(int minmag) { this.minmag = minmag; }

    public void setLat(long lat) { this.lat = lat; }

    public void setLon(long lon) { this.lon = lon; }

    public void setLocation(String location) { this.location = location; }
}
