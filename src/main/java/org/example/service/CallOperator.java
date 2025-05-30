package org.example.service;
import org.example.view.EmergencyGUI;
import org.example.model.Emergency;
import org.example.model.EmergencyType;
import java.util.Random;
/**
 * Hilo que simula operadores recibiendo llamadas de emergencia.
 * - Genera emergencias aleatorias (tipo, ubicación, distancia)
 * - Las coloca en la EmergencyQueue
 * - Espera entre 1-3 segundos entre llamadas
 */
public class CallOperator implements Runnable {
    private EmergencyQueue queue;
    private Random random = new Random();

    // Añade este constructor
    public CallOperator(EmergencyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            EmergencyType type = EmergencyType.values()[random.nextInt(4)];
            String location = "Zone-" + random.nextInt(10);
            double distance = 1 + random.nextDouble() * 20; // Distancia entre 1-20 km
            Emergency emergency = new Emergency("E-" + System.currentTimeMillis(),
                    type, location, distance);

            try {
                queue.put(emergency);
                EmergencyGUI.appendToLog("Nueva emergencia: " + emergency +
                        " | Distancia: " + String.format("%.2f km", distance));
                Thread.sleep(1000 + random.nextInt(2000)); // Llamadas cada 1-3 segundos
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
