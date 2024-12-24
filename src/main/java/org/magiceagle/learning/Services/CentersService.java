package org.magiceagle.learning.Services;

import org.magiceagle.learning.DTO.CenterDistanceDTO;
import org.magiceagle.learning.DTO.MessagueDTO;
import org.magiceagle.learning.DTO.ResourceNotFoundException;
import org.magiceagle.learning.Entities.Center;
import org.magiceagle.learning.Entities.Coordinates;
import org.magiceagle.learning.Repository.CentersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CentersService {

   private final CentersRepo centersRepo;

    public CentersService(CentersRepo centersRepo) {
        this.centersRepo = centersRepo;
    }

    public Center saveCenter(Center center) {
        return centersRepo.save(center);
    }

    public List<Center> allCenters() {
        return centersRepo.findAll();
    }

    public CenterDistanceDTO getClosest(Coordinates b) {
        List<CenterDistanceDTO> centersDistance = centersDistances(b);
        CenterDistanceDTO nearest = centersDistance.get(0);

        for (int i = 0; i < centersDistance.size(); i++) {
            System.out.println("Comparing nearest: "+nearest.getDistancia()+" To "+centersDistance.get(i).getDistancia());
            if (nearest.getDistancia() > centersDistance.get(i).getDistancia()) {
                nearest = centersDistance.get(i);
            }
        }

        return nearest;
    }

    public List<CenterDistanceDTO> centersDistances(Coordinates b) {
        List<CenterDistanceDTO> centersDistance = new ArrayList<>();
        List<Center> centers = centersRepo.findAll();

        for (int i = 0; i < centers.size(); i++) {
            Center center = centers.get(i);
            CenterDistanceDTO centerDistance = new CenterDistanceDTO();
            int distance = haversine(b, center.getCoordinates());

            centerDistance.setId(center.getId());
            centerDistance.setNombre(center.getName());
            centerDistance.setDistancia(distance);
            
            centersDistance.add(centerDistance);
        }

        return centersDistance;
    }


    // ONLY THE AVIABLE OPTIONS
    public CenterDistanceDTO getClosestAviable(Coordinates b, String capacity) {
        List<CenterDistanceDTO> centersDistance = centersDistancesAviable(b, capacity);

        // Si no hay centros disponibles, lanzar excepción
        if (centersDistance.isEmpty()) {
           return null;
        }

        CenterDistanceDTO nearest = centersDistance.get(0);
        for (int i = 0; i < centersDistance.size(); i++) {
            System.out.println("Comparing nearest: "+nearest.getDistancia()+" To "+centersDistance.get(i).getDistancia());
            if (nearest.getDistancia() > centersDistance.get(i).getDistancia()) {
                nearest = centersDistance.get(i);
            }
        }

        return nearest;
    }

    // ONLY THE AVIABLE OPTIONS
    public List<CenterDistanceDTO> centersDistancesAviable(Coordinates b, String capacity) {
        List<CenterDistanceDTO> centersDistance = new ArrayList<>();
        List<Center> centers = centersRepo.findAvilableCenters(capacity);

        for (int i = 0; i < centers.size(); i++) {
            Center center = centers.get(i);
            CenterDistanceDTO centerDistance = new CenterDistanceDTO();
            int distance = haversine(b, center.getCoordinates());

            centerDistance.setId(center.getId());
            centerDistance.setNombre(center.getName());
            centerDistance.setDistancia(distance);

            centersDistance.add(centerDistance);
        }

        return centersDistance;
    }

    public int testCenter(Long id) {
        Center selectedCenter = centersRepo.getById(id);

        Coordinates a = selectedCenter.getCoordinates();
        Coordinates b = new Coordinates();
        b.setLatitude(42.3601);
        b.setLongitude(-71.0589);

        // from client
        System.out.print("latituded A: "+a.getLongitude()+" ");
        System.out.print("longitude A: "+a.getLatitude());

        System.out.println(" ");

        System.out.print("latituded B: "+b.getLongitude()+" ");
        System.out.print("longitude B: "+b.getLatitude());

        return haversine(a, b);
    }

    public String getStatus(Long id) {
        Center center = centersRepo.getById(id);

        return center.getStatus();
    }

    public int haversine(Coordinates cordinateA, Coordinates cordinateB) {
        int radioTierra = 6371;
        double rad = Math.PI / 180;

        // Convertir las latitudes y longitudes de grados a radianes
        double lat1Rad = cordinateA.getLatitude() * rad;
        double lon1Rad = cordinateA.getLongitude() * rad;
        double lat2Rad = cordinateB.getLatitude() * rad;
        double lon2Rad = cordinateB.getLongitude() * rad;

        // Diferencia entre las latitudes y longitudes
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Aplicar la fórmula Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calcular la distancia
        double distancia = radioTierra * c;

        // Retornar la distancia como entero (kilómetros)
        return (int) distancia;
    }

    public ResponseEntity<Center> updateCenter(Long id, Map<String, Object> updates) {
       Center existingCenter = centersRepo.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Center not found"));

       updates.forEach((key, value) -> {
           switch(key) {
               case "capacity":
                   existingCenter.setCapacity((String) value);
                   break;
               case "coordinates":
                   Map<String, Object> coords = (Map<String, Object>) value;
                   Coordinates coordinates = new Coordinates();
                   coordinates.setLongitude((Double) coords.get("longitude"));
                   coordinates.setLatitude((Double) coords.get("latitude"));
                   existingCenter.setCoordinates(coordinates);
                   break;
               case "name":
                   existingCenter.setName((String) value);
                   break;
               case "currentLoad":
                   existingCenter.setCurrentLoad((int) value);
                   break;
               case "maxCapacity":
                   existingCenter.setMaxCapacity((int) value);
                   break;
               case "status":
                   existingCenter.setStatus((String) value);
                   break;
           }
       });

        Center updated = centersRepo.save(existingCenter);

        return ResponseEntity.ok(updated);
    }
}
