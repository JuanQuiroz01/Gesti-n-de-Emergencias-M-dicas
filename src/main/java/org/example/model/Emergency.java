package org.example.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 * Representa una emergencia médica con:
 * - ID único
 * - Tipo (gravedad)
 * - Ubicación
 * - Distancia desde el centro
 * - Marca de tiempo
 *
 * Implementa Comparable para priorización automática en la cola:
 * 1. Prioridad (mayor primero)
 * 2. Tiempo de espera (más antiguo primero)
 * 3. Distancia (más cercano primero)
 */
public class Emergency implements Comparable<Emergency> {
    private String id;
    private EmergencyType type;
    private String location;
    private LocalDateTime timestamp;
    private double distance;


    public Emergency(String id, EmergencyType type, String location, double distance) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.distance = distance;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public int compareTo(Emergency other) {
        // Primero por prioridad (mayor primero)
        int priorityCompare = Integer.compare(other.type.getPriority(), this.type.getPriority());
        if (priorityCompare != 0) return priorityCompare;

        // Luego por tiempo de espera (más antiguo primero)
        long timeDiff = ChronoUnit.SECONDS.between(this.timestamp, other.timestamp);
        if (timeDiff != 0) return (int)timeDiff;

        // Finalmente por distancia (más cercano primero)
        return Double.compare(this.distance, other.distance);
    }

    // Getters y toString()


    @Override
    public String toString() {
        return "Emergency{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }




    public EmergencyType getType() {
        return type;
    }



    public String getLocation() {
        return location;
    }



    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getDistance() {
        return distance;
    }
}



