package org.example.view;

import javax.swing.*;
import java.awt.*;
import org.example.service.ResourceManager;
/**
 * Interfaz gráfica que muestra:
 * - Log de eventos en tiempo real
 * - Estadísticas actualizadas cada segundo:
 *   - Emergencias atendidas/rechazadas
 *   - Ambulancias disponibles/en uso
 *
 * Usa SwingUtilities.invokeLater para actualizaciones thread-safe
 */

public class EmergencyGUI {
    private static JTextArea logArea;
    private static JLabel statsLabel;
    private static ResourceManager resourceManager;

    public static void launchGUI(ResourceManager manager) {
        resourceManager = manager;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Centro de Emergencias - Monitoreo en Tiempo Real");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout());

            // Panel de estadísticas
            JPanel statsPanel = new JPanel();
            statsLabel = new JLabel("Cargando estadísticas...");
            statsPanel.add(statsLabel);
            mainPanel.add(statsPanel, BorderLayout.NORTH);

            // Área de logs
            logArea = new JTextArea();
            logArea.setEditable(false);
            mainPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

            frame.add(mainPanel);
            frame.setVisible(true);

            // Actualizar estadísticas cada segundo
            new Timer(1000, e -> updateStats()).start();
        });
    }

    private static void updateStats() {
        if (resourceManager != null && statsLabel != null) {
            statsLabel.setText(resourceManager.getStats());
        }
    }

    public static void appendToLog(String message) {
        SwingUtilities.invokeLater(() -> {
            if (logArea != null) {
                logArea.append(message + "\n");
                logArea.setCaretPosition(logArea.getDocument().getLength());
            }
        });
    }
}