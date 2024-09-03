public class Main {

    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        Thread r1 = new Thread(new MyRunnable());
        Thread r2 = new Thread(new MyRunnable());
        Thread r3 = new Thread(new MyRunnable());

        t1.start();
        t2.start();
        t3.start();
        r1.start();
        r2.start();
        r3.start();

    }
}
