import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class PersonForm extends JFrame {

    private static ArrayList<Person> people = new ArrayList<>();

    PersonForm() {

        // Form to be filled:

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name ");
        JTextField nameField = new JTextField(10);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel genderLabel = new JLabel("Gender ");
        ButtonGroup genderGroup = new ButtonGroup();
        JRadioButton maleBox = new JRadioButton("Male");
        JRadioButton femaleBox = new JRadioButton("Female");
        genderGroup.add(maleBox);
        genderGroup.add(femaleBox);
        genderPanel.add(genderLabel);
        genderPanel.add(maleBox);
        genderPanel.add(femaleBox);

        JPanel jobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jobDescLabel = new JLabel("Job Description ");
        JTextArea jobDescTextArea = new JTextArea(5, 20);
        jobPanel.add(jobDescLabel);
        jobPanel.add(jobDescTextArea);

        JPanel xpPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel xpLabel = new JLabel("Experience in years ");
        JScrollBar xpBar = new JScrollBar(0, 0, 0, 0, 100);
        JTextField xpField = new JTextField(2);
        xpField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int xp = Integer.parseInt(xpField.getText());
                xpBar.setValue(xp);
            }
        });
        xpBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                xpField.setText(String.valueOf(e.getValue()));
            }
        });

        xpPanel.add(xpLabel);
        xpPanel.add(xpField);
        xpPanel.add(xpBar);

        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationLabel = new JLabel("Location ");
        String[] locations = { "Bucharest", "Cluj", "Brasov", "Iasi", "Sinaia", "Constanta", "Craiova" };
        JComboBox locationChoice = new JComboBox(locations);
        locationPanel.add(locationLabel);
        locationPanel.add(locationChoice);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                people.add(new Person(nameField.getText(), maleBox.isSelected() ? "Male" : "Female",
                        jobDescTextArea.getText(), Integer.parseInt(xpField.getText()),
                        locationChoice.getSelectedItem().toString()));

                nameField.setText("");
                genderGroup.clearSelection();
                jobDescTextArea.setText("");
                xpField.setText("0");
                locationChoice.setSelectedItem("Bucharest");
            }
        });

        JButton showAllButton = new JButton("Show All");
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                people.forEach(p -> {
                    System.out.println("\n" + p.toString() + "\n");
                });
                new ResultsFrame(people);
            }
        });

        JButton saveAllButton = new JButton("Save All");
        saveAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                FileWriter fw;
                BufferedWriter bw;

                try {

                    fw = new FileWriter("output.txt");
                    bw = new BufferedWriter(fw);

                    people.forEach(person -> {
                        try {

                            bw.write(person.toString() + "\n\n");
                        } catch (IOException exec) {

                        }
                    });

                    bw.close();

                } catch (IOException ex) {

                    System.out.println("Something went wrong while trying to save the list of people in a txt file:\n\n"
                            + ex.getMessage());
                }

            }
        });

        buttonsPanel.add(addButton);
        buttonsPanel.add(showAllButton);
        buttonsPanel.add(saveAllButton);

        // Layout details
        setLayout(new GridLayout(0, 1, 0, 20));

        // Make program exit when I close the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Adding all panels to the main frame
        add(namePanel);
        add(genderPanel);
        add(jobPanel);
        add(xpPanel);
        add(locationPanel);
        add(buttonsPanel);

        pack();
        setTitle("Person Form");
        setVisible(true);
    }

    public static void main(String[] args) {
        new PersonForm();
    }
}