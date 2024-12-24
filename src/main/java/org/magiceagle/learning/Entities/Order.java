package org.magiceagle.learning.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long customerId;

    private String size;

    private String Status;

    @Embedded
    private Coordinates coordinates;

    @ManyToOne
    private Center asignedCenter;

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
}
