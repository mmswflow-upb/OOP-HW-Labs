public class EmbassyEmployee extends Thread {

    private Embassy embassy;

    public EmbassyEmployee(Embassy embassy) {
        this.embassy = embassy;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            embassy.processVisaApplication();
        }
    }

}
