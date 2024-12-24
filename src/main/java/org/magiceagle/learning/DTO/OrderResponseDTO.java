package org.magiceagle.learning.DTO;

import org.magiceagle.learning.Entities.Center;
import org.magiceagle.learning.Entities.Coordinates;

public class OrderResponseDTO {
    private long id;

    private Long customerId;

    private String size;

    private String Status;

    private Coordinates coordinates;

    private Center asignedCenter;

    private String messague;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setAsignedCenter(Center asignedCenter) {
        this.asignedCenter = asignedCenter;
    }

    public Center getAsignedCenter() {
        return asignedCenter;
    }

    public void setMessague(String messague) {
        this.messague = messague;
    }

    public String getMessague() {
        return messague;
    }
}