package org.magiceagle.learning.Controllers;

import org.magiceagle.learning.DTO.AllocationDTO;
import org.magiceagle.learning.DTO.OrderResponseDTO;
import org.magiceagle.learning.Entities.Order;
import org.magiceagle.learning.Services.CentersService;
import org.magiceagle.learning.Services.OrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    public OrdersService ordersService;
    public CentersService centersService;

    public OrderController(OrdersService ordersService, CentersService centersService) {
        this.ordersService = ordersService;
        this.centersService = centersService;
    }

    @PostMapping("/orders/create")
    public OrderResponseDTO createOrder(@RequestBody Order order) {
        return ordersService.createOrder(order);
    }

    @GetMapping("/orders/alllocation")
    public AllocationDTO allocate() {
        return ordersService.allocation();
    }
}
