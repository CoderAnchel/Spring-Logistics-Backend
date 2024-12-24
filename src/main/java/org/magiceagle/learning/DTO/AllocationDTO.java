package org.magiceagle.learning.DTO;

import org.magiceagle.learning.Entities.Order;

import java.util.ArrayList;
import java.util.List;

public class AllocationDTO {
    public String description = "procesed-orders";
    private List<OrderResponseDTO> orders =  new ArrayList<>();

    public void setOrder(OrderResponseDTO order) {
        this.orders.add(order);
    }

    public void setOrders(List<OrderResponseDTO> orders) {
        this.orders = orders;
    }

    public List<OrderResponseDTO> getOrders() {
        return orders;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
