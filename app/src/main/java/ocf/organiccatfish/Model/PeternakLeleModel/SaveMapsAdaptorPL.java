package ocf.organiccatfish.Model.PeternakLeleModel;

public class SaveMapsAdaptorPL {

    private double latitude, longitude;

    public SaveMapsAdaptorPL(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}