package org.magiceagle.learning.Services;

import org.magiceagle.learning.DTO.AllocationDTO;
import org.magiceagle.learning.DTO.CenterDistanceDTO;
import org.magiceagle.learning.DTO.OrderResponseDTO;
import org.magiceagle.learning.Entities.Center;
import org.magiceagle.learning.Entities.Order;
import org.magiceagle.learning.Repository.CentersRepo;
import org.magiceagle.learning.Repository.OrdersRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final CentersRepo centersRepo;
    private final OrdersRepo ordersRepo;
    private CentersService centersService;

    public OrdersService(OrdersRepo ordersRepo, CentersService centersService, CentersRepo centersRepo) {
        this.ordersRepo = ordersRepo;
        this.centersService = centersService;
        this.centersRepo = centersRepo;
    }

    public String saludo() {
        return "Hola desde el servicio de ordenes!";
    }

    public OrderResponseDTO createOrder(Order order) {
        // Obtener el centro más cercano disponible
        CenterDistanceDTO closestAviable = centersService.getClosestAviable(order.getCoordinates(), order.getSize());

        // Si no hay centros disponibles
        if (closestAviable == null) {
            OrderResponseDTO response = new OrderResponseDTO();
            order.setStatus("Pending");

            // Guardar la orden en estado pendiente
            Order pedido = ordersRepo.save(order);

            // Construir la respuesta
            response.setId(pedido.getId());
            response.setCustomerId(pedido.getCustomerId());
            response.setSize(pedido.getSize());
            response.setCoordinates(pedido.getCoordinates());
            response.setMessague("Order created but no centers available");
            return response; // Devolver respuesta con mensaje
        }

        // Si hay un centro disponible
        Center center = centersRepo.getById(closestAviable.getId());
        center.setCurrentLoad(center.getCurrentLoad() + 1);

        // Actualizar el estado del centro si está lleno
        if (center.getCurrentLoad() >= center.getMaxCapacity()) {
            center.setStatus("Full");
        }

        // Asignar el centro a la orden
        order.setAsignedCenter(center);
        order.setStatus("Asigned");

        // Guardar el centro y la orden
        centersRepo.save(center);
        Order pedido = ordersRepo.save(order);

        // Construir la respuesta
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(pedido.getId());
        response.setCustomerId(pedido.getCustomerId());
        response.setSize(pedido.getSize());
        response.setCoordinates(pedido.getCoordinates());
        response.setAsignedCenter(pedido.getAsignedCenter());
        response.setMessague("Order Created!");
        return response;
    }

    public AllocationDTO allocation() {
        AllocationDTO allocationDTO = new AllocationDTO();
        List<Order> pendingOrders = ordersRepo.findPending();

        for (int i = 0; i < pendingOrders.size(); i++) {
            Order order = pendingOrders.get(i);
            OrderResponseDTO orderResponseDTO = createOrder(order);
            allocationDTO.setOrder(orderResponseDTO);
        }

        return allocationDTO;
    }

}
