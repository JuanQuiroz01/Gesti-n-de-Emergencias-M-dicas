package org.example;

import org.example.service.*;
import org.example.view.EmergencyGUI;

public class Main {
    public static void main(String[] args) {
        EmergencyQueue queue = new EmergencyQueue();
        ResourceManager manager = new ResourceManager(5); // 5 ambulancias

        EmergencyGUI.launchGUI(manager);

        // Lanzar múltiples operadores
        for (int i = 0; i < 3; i++) {
            new Thread(new CallOperator(queue), "Operador-" + i).start();
        }

        // Lanzar despachadores
        for (int i = 0; i < 2; i++) {
            new Thread(new Dispatcher(queue, manager), "Despachador-" + i).start();
        }
    }
}