package org.magiceagle.learning.DTO;

import org.magiceagle.learning.Entities.Coordinates;

public class AviabilityDTO {
    private Coordinates coordinates;
    private String capacity;

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }
}
