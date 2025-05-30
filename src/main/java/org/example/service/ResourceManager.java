package org.example.service;

import org.example.model.Ambulance;
import org.example.view.EmergencyGUI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Gestiona los recursos limitados (ambulancias):
 * - Usa Semaphore para controlar el acceso
 * - Mantiene estadísticas de uso
 * - Implementa adquisición con tiempo de espera para evitar deadlocks
 * - Sincroniza el acceso a la lista de ambulancias
 */
public class ResourceManager {
    private final List<Ambulance> ambulances;
    private final Semaphore semaphore;
    private final AtomicInteger attendedEmergencies = new AtomicInteger(0);
    private final AtomicInteger rejectedEmergencies = new AtomicInteger(0);
    private final AtomicInteger totalEmergencies = new AtomicInteger(0);

    public ResourceManager(int ambulanceCount) {
        this.ambulances = new ArrayList<>();
        this.semaphore = new Semaphore(ambulanceCount, true);

        for (int i = 1; i <= ambulanceCount; i++) {
            ambulances.add(new Ambulance("AMB-" + i));
        }
    }
    // En ResourceManager (adquisición de ambulancia con Semaphore y synchronized)
    public Ambulance acquireAmbulance() throws InterruptedException {
        totalEmergencies.incrementAndGet();

        if (!semaphore.tryAcquire()) { // Evita bloqueo indefinido
            rejectedEmergencies.incrementAndGet();
            EmergencyGUI.appendToLog("EMERGENCIA RECHAZADA - No hay ambulancias disponibles");
            return null;
        }

        synchronized (this) {
            for (Ambulance ambulance : ambulances) {
                if (ambulance.isAvailable()) {
                    ambulance.setAvailable(false);
                    attendedEmergencies.incrementAndGet();
                    EmergencyGUI.appendToLog("Ambulancia " + ambulance.getId() + " asignada");
                    return ambulance;
                }
            }
        }

        semaphore.release(); // Fallback si no se encontró ambulancia disponible
        rejectedEmergencies.incrementAndGet();
        return null;
    }

    public void releaseAmbulance(Ambulance ambulance) {
        if (ambulance != null) {
            synchronized (this) {
                ambulance.setAvailable(true);
            }
            semaphore.release();
        }
    }

    public String getStats() {
        int available = semaphore.availablePermits();
        int total = ambulances.size();
        int attended = attendedEmergencies.get();
        int rejected = rejectedEmergencies.get();
        int processing = total - available;

        return String.format("Atendidas: %d | Rechazadas: %d | En proceso: %d | Disponibles: %d/%d",
                attended, rejected, processing, available, total);
    }
}
