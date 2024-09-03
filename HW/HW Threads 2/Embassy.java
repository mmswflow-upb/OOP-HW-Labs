import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class Embassy {

    private LinkedList<Visa> visaApplicationsQueue;
    private int maxCandidates;

    private Object lock;

    private ExecutorService executor;

    Embassy(int maxCandidates, int maxEmployees) {

        this.maxCandidates = maxCandidates;

        visaApplicationsQueue = new LinkedList<Visa>();

        lock = new Object();

        executor = Executors.newFixedThreadPool(maxEmployees + maxCandidates);

        for (int i = 0; i < maxEmployees; i++) {
            executor.submit(new EmbassyEmployee(this));
        }

        Random random = new Random();

        for (int i = 0; i < maxCandidates; i++) {

            int numberOfApplications = random.nextInt(1, 4);// from 1 to 3
            String firstName = "Candidate No.";
            String lastName = String.valueOf((i + 1));
            int age = random.nextInt(21, 151);// from 21 to 150
            String passportNumber = "Pass.No." + (i + 1);
            Candidate tempCandidate = new Candidate(this, firstName, lastName, age, passportNumber, 0,
                    numberOfApplications);
            executor.submit(tempCandidate);

            System.out.println(tempCandidate);
        }
    }

    public void addApplication(Visa visaApplication) {

        synchronized (lock) {

            while (visaApplicationsQueue.size() == maxCandidates) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            visaApplicationsQueue.add(visaApplication);
            lock.notifyAll();

        }

    }

    public void processVisaApplication() {

        synchronized (lock) {
            while (visaApplicationsQueue.size() == 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Visa rejectedVisaApp = visaApplicationsQueue.removeFirst();

            lock.notifyAll();

            if (rejectedVisaApp.getEducationLevel() > 3) {
                createVisaFile(rejectedVisaApp);
            } else {
                addToRejected(rejectedVisaApp);
            }
        }

    }

    public void createVisaFile(Visa acceptedVisaApp) {

        FileWriter writer;
        BufferedWriter bw;

        try {

            writer = new FileWriter(
                    "visas\\visa_" + acceptedVisaApp.getFirstName() + acceptedVisaApp.getLastName() + "_ID"
                            + acceptedVisaApp.getVisaId(),
                    false);
            bw = new BufferedWriter(writer);
            bw.write(acceptedVisaApp.toString());

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToRejected(Visa rejectedVisaApp) {

        FileWriter writer;
        BufferedWriter bw;

        try {

            writer = new FileWriter("rejected_candidates.txt", true);
            bw = new BufferedWriter(writer);
            bw.write("Full Name: " + rejectedVisaApp.getFirstName() + rejectedVisaApp.getLastName()

                    + " | Visa ID: " + rejectedVisaApp.getVisaId() + " | Education Level: "
                    + rejectedVisaApp.getEducationLevel() + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        Embassy embassy = new Embassy(3, 10);

    }
}