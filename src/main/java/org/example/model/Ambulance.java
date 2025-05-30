package org.example.model;
/**
 * Representa una ambulancia con:
 * - ID único
 * - Estado de disponibilidad
 *
 * Proporciona métodos para gestionar su asignación
 */
public class Ambulance {
    private String id;
    private boolean available;

    public Ambulance(String id) {
        this.id = id;
        this.available = true;
    }
    // Getters y setters
    @Override
    public String toString() {
        return "Ambulance-" + id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}