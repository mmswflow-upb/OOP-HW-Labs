import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class ResultsFrame extends JFrame {

    ResultsFrame(ArrayList<Person> people) {

        people.forEach(person -> {

            JPanel panel = new JPanel(new FlowLayout());

            panel.add(new Label("Name: " + person.getName()));
            panel.add(new Label("Gender: " + person.getGender()));
            panel.add(new Label("Job Description: " + person.getJobDescription()));
            panel.add(new Label("Experience in Years: " + person.getExp()));
            panel.add(new Label("Location: " + person.getLocation()));

            add(panel);
        });

        // Make window close when we exit it
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLayout(new GridLayout(0, 1, 0, 5));

        pack();
        setTitle("List of People");
        setVisible(true);
    }

}
