import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

public class Main extends JFrame {

    // Sensor System
    private Eventqueue eventsQueue;
    private ArrayList<Sensor> sensors;
    private Dispatcher dispatcher;
    private ExecutorService executor;

    // Panels for managing layout

    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonsPanel;
    private JPanel notificationsPanel;

    // Where the SensorEvents will be shown
    private JLabel notificationsLabel;
    private JPanel notificationsLabelPanel;// Used to center the text
    private JTextArea notificationsTextArea;
    private JScrollPane notificationsTextAreaPane;

    // Buttons that enable/disable sensors
    private ArrayList<JButton> sensors_btns;

    // Constructor
    public Main() {

        // Defining the Sensors, Dispatcher, Executor, Lock
        {
            eventsQueue = new Eventqueue(10, this);

            dispatcher = new Dispatcher(eventsQueue);

            sensors = new ArrayList<>(Arrays.asList(new Sensor(0, "Light", eventsQueue),
                    new Sensor(1, "Smoke", eventsQueue),
                    new Sensor(2, "Movement", eventsQueue),
                    new Sensor(3, "Gas", eventsQueue)));

            executor = Executors.newFixedThreadPool(5);

            for (Sensor s : sensors) {
                executor.submit(s);
            }
            executor.submit(dispatcher);
            executor.shutdown();
        }

        // Creating the UI

        // Title Part
        {
            titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titleLabel = new JLabel("My Sensor App");
            titleLabel.setFont(new Font("Monospace", Font.ITALIC, 30));
            titlePanel.add(titleLabel);
        }

        // Notifications Part
        {
            notificationsPanel = new JPanel(new GridLayout(2, 1));
            notificationsLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            notificationsLabel = new JLabel("Notifications: ");
            notificationsLabel.setFont(new Font("Monospace", Font.BOLD, 20));

            notificationsTextArea = new JTextArea(20, 10);
            notificationsTextArea.setEditable(false);
            notificationsTextArea.setFont(new Font("Courier New", Font.PLAIN, 18));
            notificationsTextAreaPane = new JScrollPane(notificationsTextArea);

            notificationsLabelPanel.add(notificationsLabel);

            notificationsPanel.add(notificationsLabelPanel);
            notificationsPanel.add(notificationsTextAreaPane);

        }

        // Buttons Part
        {
            buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonsPanel.setBackground(new Color(200, 200, 200));

            sensors_btns = new ArrayList<>(4);

            for (int i = 0; i < 4; i++) {

                JButton tempButton = new JButton("Sensor " + i);
                tempButton.setBackground(Color.RED);
                tempButton.setFocusable(false);

                sensors_btns.add(tempButton);
                buttonsPanel.add(tempButton);

                // Handling click events on buttons
                tempButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Sensor tempSensor = sensors.get(sensors_btns.indexOf(tempButton));

                        if (tempSensor.isActive()) {

                            tempButton.setBackground(Color.RED);
                            tempSensor.setActive(false);

                        } else {
                            tempButton.setBackground(Color.GREEN);
                            tempSensor.setActive(true);

                        }
                    }
                });
            }
        }

        // Setting layout of main frame
        {
            setBackground(new Color(60, 60, 60));
            setLocationRelativeTo(null);
            setTitle("House Sensors System");
            setMinimumSize(new Dimension(500, 500));
        }

        // adding all panels to frame
        {
            add(titlePanel, BorderLayout.NORTH);
            add(notificationsPanel, BorderLayout.CENTER);
            add(buttonsPanel, BorderLayout.SOUTH);
        }

        // Making program stop running when exiting window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Making app visible
        setVisible(true);
    }

    // Notify the program that changes have been made to the queue ( dispatcher
    // handles events)
    public void notifyUI(String notification) {

        notificationsTextArea.append(notification + "\n");
    }

    public static void main(String[] args) {
        new Main();
    }
}