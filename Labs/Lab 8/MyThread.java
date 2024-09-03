public class MyThread extends Thread {

    public void run() {

        while (!isInterrupted()) {

            try {

                Thread.sleep(10 * 1000);
            } catch (InterruptedException exec) {
                exec.printStackTrace();
            }
            System.out.println(getName() + " :" + System.currentTimeMillis());
        }

    }

}
