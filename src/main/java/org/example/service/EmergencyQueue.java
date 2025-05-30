package org.example.service;

import org.example.model.Emergency;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
/**
 * Implementación de BlockingQueue que prioriza emergencias.
 * - Usa internamente PriorityBlockingQueue
 * - Ordena según el compareTo de Emergency
 * - Proporciona todos los métodos de BlockingQueue de forma thread-safe
 */
public class EmergencyQueue implements BlockingQueue<Emergency> {
    private final PriorityBlockingQueue<Emergency> queue = new PriorityBlockingQueue<>();

    // Método iterator() requerido
    @Override
    public Iterator<Emergency> iterator() {
        return queue.iterator();
    }

    // Resto de métodos de BlockingQueue (como en la solución anterior)
    @Override
    public boolean add(Emergency emergency) {
        return queue.add(emergency);
    }

    @Override
    public boolean offer(Emergency emergency) {
        return queue.offer(emergency);
    }

    @Override
    public void put(Emergency emergency) throws InterruptedException {
        queue.put(emergency);
    }

    @Override
    public boolean offer(Emergency emergency, long timeout, TimeUnit unit) throws InterruptedException {
        return queue.offer(emergency, timeout, unit);
    }

    @Override
    public Emergency take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public Emergency poll(long timeout, TimeUnit unit) throws InterruptedException {
        return queue.poll(timeout, unit);
    }

    @Override
    public int remainingCapacity() {
        return queue.remainingCapacity();
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public int drainTo(java.util.Collection<? super Emergency> c) {
        return queue.drainTo(c);
    }

    @Override
    public int drainTo(java.util.Collection<? super Emergency> c, int maxElements) {
        return queue.drainTo(c, maxElements);
    }

    @Override
    public Emergency remove() {
        return queue.remove();
    }

    @Override
    public Emergency poll() {
        return queue.poll();
    }

    @Override
    public Emergency element() {
        return queue.element();
    }

    @Override
    public Emergency peek() {
        return queue.peek();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean addAll(java.util.Collection<? extends Emergency> c) {
        return queue.addAll(c);
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }
}