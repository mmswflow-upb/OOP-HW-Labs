import java.util.*;
import java.util.concurrent.locks.*;

public class Eventqueue {

    private final int LIMIT;
    private final Main program;

    private Queue<SensorEvent> eventsQueue;
    private Lock lock = new ReentrantLock();
    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();

    // Queue is empty, dispatcher needs to wait for sensors to add events
    // We don't want to lose the events, even when the queue is full
    // Queue is full, sensors need to wait for dipatcher to remove events
    // We don't want to lose our dispatcher's attempt of removing events

    Eventqueue(int limit, Main program) {

        LIMIT = limit;
        this.program = program;
        eventsQueue = new LinkedList<>();
    }

    public void add(SensorEvent se) {

        lock.lock();

        try {
            while (eventsQueue.size() == LIMIT) {
                full.await();// Wait for dispatcher to remove at least an event
            }

            eventsQueue.add(se);
            empty.signal();

        } catch (

        InterruptedException exec) {

            exec.printStackTrace();

        } finally {
            lock.unlock();
        }
    }

    public void remove() {

        lock.lock();

        try {

            while (eventsQueue.isEmpty()) {

                empty.await();// Wait for Sensors to provide events
            }
            SensorEvent removedEvent = eventsQueue.remove();

            program.notifyUI(removedEvent.toString());
            full.signal();// Signaling sensors that they can add events (no longer full)

        } catch (InterruptedException exec) {
            Thread.currentThread().interrupt();
            exec.printStackTrace();

        } finally {

            lock.unlock();
        }
    }
}