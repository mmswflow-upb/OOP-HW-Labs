public class MyRunnable implements Runnable {

    public void run() {
        while (true) {
            try {

                Thread.sleep(10 * 1000);
            } catch (InterruptedException exec) {
                exec.printStackTrace();
            }
            System.out.println("Runnable: " + System.currentTimeMillis());

        }

    }
}
