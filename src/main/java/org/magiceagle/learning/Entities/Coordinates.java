package org.magiceagle.learning.Entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinates {
    private Double latitude;
    private Double longitude;

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitude() {
       return  this.latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return  this.longitude;
    }
}
