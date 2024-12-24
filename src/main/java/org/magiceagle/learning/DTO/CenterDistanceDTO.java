package org.magiceagle.learning.DTO;

public class CenterDistanceDTO {
    private Long id;
    private String nombre;
    private int distancia;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDistancia() {
        return distancia;
    }

}
