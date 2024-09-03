import java.util.*;

public class Sensor extends Thread {

    private int sensorId;
    private String type;
    private Eventqueue eventsQueue;
    private volatile boolean active;// We might deactivate the sensor at runtime, from the UI, then re-activate it

    public Sensor(int id, String type, Eventqueue eq) {
        super("Sensor " + id + " of Type: " + type);
        sensorId = id;
        this.type = type;
        eventsQueue = eq;
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void produceEvent() {
        // Time should be in seconds (easier to read)
        eventsQueue.add(new SensorEvent(sensorId, type, System.currentTimeMillis() / 1_000_000_000_000.0));
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {

            while (active) {
                produceEvent();// add a new event to the queue of events
                try {
                    Thread.sleep(random.nextInt(1000));// Might sleep 0-999 ms
                } catch (InterruptedException exec) {
                    exec.printStackTrace();

                }
            }

        }
    }
}