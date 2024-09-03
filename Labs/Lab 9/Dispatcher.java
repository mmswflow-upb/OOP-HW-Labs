public class Dispatcher implements Runnable {

    private Eventqueue eventsQueue;

    public Dispatcher(Eventqueue eventsQueue) {

        this.eventsQueue = eventsQueue;
    }

    // It tries to remove events every 50 ms
    @Override
    public void run() {
        while (true) {

            eventsQueue.remove();
            try {

                Thread.sleep(50);

            } catch (InterruptedException exec) {
                exec.printStackTrace();
            }
        }
    }

}