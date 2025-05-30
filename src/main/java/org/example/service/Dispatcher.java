package org.example.service;

import org.example.model.Emergency;
import org.example.model.Ambulance;
import org.example.view.EmergencyGUI;
import java.util.concurrent.BlockingQueue;
/**
 * Hilo que atiende emergencias desde la cola:
 * 1. Toma la emergencia más prioritaria
 * 2. Intenta adquirir una ambulancia disponible
 * 3. Simula el tiempo de respuesta (basado en prioridad y distancia)
 * 4. Libera la ambulancia cuando termina
 */
public class Dispatcher implements Runnable {
    private final BlockingQueue<Emergency> emergencyQueue;
    private final ResourceManager resourceManager;

    public Dispatcher(BlockingQueue<Emergency> emergencyQueue, ResourceManager resourceManager) {
        this.emergencyQueue = emergencyQueue;
        this.resourceManager = resourceManager;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Emergency emergency = emergencyQueue.take();
                EmergencyGUI.appendToLog("Atendiendo: " + emergency);

                Ambulance ambulance = resourceManager.acquireAmbulance();
                if (ambulance == null) {
                    EmergencyGUI.appendToLog("ERROR: No hay ambulancias disponibles para " + emergency);
                    continue;
                }

                EmergencyGUI.appendToLog("Asignando " + ambulance.getId() + " a " + emergency.getId());
                simulateResponse(emergency, ambulance);

                resourceManager.releaseAmbulance(ambulance);
                EmergencyGUI.appendToLog("Finalizado: " + emergency.getId() +
                        " | Ambulancia " + ambulance.getId() + " liberada");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void simulateResponse(Emergency emergency, Ambulance ambulance)
            throws InterruptedException {
        // Tiempo basado en prioridad y distancia
        double baseTime = (4 - emergency.getType().getPriority()) * 1000;
        double distanceTime = emergency.getDistance() * 500;
        int totalTime = (int)(baseTime + distanceTime);

        EmergencyGUI.appendToLog("Tiempo estimado: " + totalTime/1000.0 + " segundos");
        Thread.sleep(totalTime);
    }
}