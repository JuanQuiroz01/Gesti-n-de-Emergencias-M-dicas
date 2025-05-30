package org.example.model;
/**
 * Niveles de gravedad de una emergencia médica.
 * - CRITICA: Amenaza vida inmediata (ej: paro cardíaco).
 * - GRAVE: Necesita atención rápida pero no es mortal (ej: fractura expuesta).
 * - MODERADA: Requiere tratamiento en horas (ej: dolor intenso).
 * - LEVE: Puede esperar (ej: esguince menor).
 */
public enum EmergencyType {
    CRITICAL(3),  // Máxima prioridad
    SEVERE(2),
    MODERATE(1),
    MILD(0);

    private final int priority;
    EmergencyType(int priority) { this.priority = priority; }
    public int getPriority() { return priority; }
}
