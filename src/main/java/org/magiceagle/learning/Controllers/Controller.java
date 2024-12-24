package org.magiceagle.learning.Controllers;

import org.magiceagle.learning.DTO.CenterDistanceDTO;
import org.magiceagle.learning.Entities.Center;
import org.magiceagle.learning.Entities.Coordinates;
import org.magiceagle.learning.Services.CentersService;
import org.magiceagle.learning.Services.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class Controller {
    public OrdersService ordersService;
    public CentersService centersService;

    public Controller( OrdersService ordersService, CentersService centersService) {
        this.ordersService = ordersService;
        this.centersService = centersService;
    }

    @GetMapping("/orders")
    public String orders() {
        return ordersService.saludo();
    }

    @GetMapping("/centers")
    public List<Center> centers() {
        return centersService.allCenters();
    }

    @PostMapping("/centers")
    public Center createCenter(@RequestBody Center center) {
        return centersService.saveCenter(center);
    }

    @GetMapping("/centers/find")
    public int testCenter(@RequestParam long id) {
        return centersService.testCenter(id);
    }

    @GetMapping("/centers/find/status")
    public String testCenter2(@RequestParam long id) {
        return centersService.getStatus(id);
    }

    @PostMapping("/centers/distance/all")
    public List<CenterDistanceDTO> centersDistance(@RequestBody Coordinates b) {
        return centersService.centersDistances(b);
    }

    @PostMapping("/centers/distance/nearest")
    public CenterDistanceDTO nearestDistance(@RequestBody Coordinates b) {
        return centersService.getClosest(b);
    }

    @PatchMapping("centers/update/{id}")
    public ResponseEntity<Center> updateCenter(@PathVariable Long id, @RequestBody Map<String, Object> updates ){
       return centersService.updateCenter(id, updates);
    }

}
