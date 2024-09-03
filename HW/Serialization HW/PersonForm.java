import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PersonForm extends JFrame {
    private JPanel namePanel;
    private JPanel surnamePanel;
    private JPanel genderPanel;
    private JPanel agePanel;
    private JPanel cityPanel;
    private JPanel countryPanel;
    private JPanel buttonsPanel;

    private ButtonGroup genderGroup;

    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel cityLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JLabel countryLabel;

    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField cityTextField;
    private JTextField ageTextField;

    private JScrollBar ageScrollBar;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JComboBox<String> countryComboBox;

    private JButton addButton;
    private JButton showAllButton;

    private FileOutputStream fos;
    private ObjectOutputStream oos;

    private boolean showingAll = false;

    private static final String peopleDataPath = "people.dat";

    private ArrayList<Person> people;

    public PersonForm() {

        // reading the people's data from previous sessions
        people = readPeopleData();

        // Create the panels
        {
            namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            surnamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            cityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            countryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        }

        // Create the labels and text fields
        {
            nameLabel = new JLabel("Name:");
            surnameLabel = new JLabel("Surname:");
            cityLabel = new JLabel("City:");
            ageLabel = new JLabel("Age:");
            genderLabel = new JLabel("Gender:");
            countryLabel = new JLabel("Country:");

            nameTextField = new JTextField(20);
            surnameTextField = new JTextField(20);
            cityTextField = new JTextField(20);
        }
        // Create the age scrollbar
        {
            ageScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 100);
            ageTextField = new JTextField("0", 3);

            ageScrollBar.setMaximum(150);

            agePanel.add(ageLabel);
            agePanel.add(ageScrollBar);
            agePanel.add(ageTextField);

        }

        // Create the gender radio buttons
        {
            maleRadioButton = new JRadioButton("Male");
            femaleRadioButton = new JRadioButton("Female");

            // Create the gender button group
            genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);

            // set the action command for the radio buttons
            maleRadioButton.setActionCommand("Male");
            femaleRadioButton.setActionCommand("Female");

            genderPanel.add(genderLabel);
            genderPanel.add(maleRadioButton);
            genderPanel.add(femaleRadioButton);

        }
        // Create the country selector
        {
            String[] countries = { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda",
                    "Argentina", "Armenia", "Australia", "Austria",
                    "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
                    "Benin",
                    "Bhutan", "Bolivia",
                    "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
                    "Cabo Verde",
                    "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia",
                    "Comoros", "Congo",
                    "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
                    "Dominican Republic",
                    "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
                    "Eswatini",
                    "Ethiopia", "Fiji",
                    "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada",
                    "Guatemala",
                    "Guinea",
                    "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran",
                    "Iraq", "Ireland",
                    "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North",
                    "Korea, South",
                    "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
                    "Liechtenstein", "Lithuania",
                    "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
                    "Mauritania", "Mauritius",
                    "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
                    "Myanmar", "Namibia", "Nauru",
                    "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Macedonia", "Norway",
                    "Oman", "Pakistan", "Palau",
                    "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar",
                    "Romania", "Russia", "Rwanda",
                    "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
                    "Sao Tome and Principe",
                    "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia",
                    "Slovenia",
                    "Solomon Islands",
                    "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden",
                    "Switzerland", "Syria",
                    "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia",
                    "Turkey",
                    "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom",
                    "United States",
                    "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia",
                    "Zimbabwe" };
            countryComboBox = new JComboBox<>(countries);
            countryPanel.add(countryLabel);
            countryPanel.add(countryComboBox);
        }
        // Add labels and text fields to the name panel
        {
            namePanel.add(nameLabel);
            namePanel.add(nameTextField);
        }
        // Add labels and text fields to the surname panel
        {
            surnamePanel.add(surnameLabel);
            surnamePanel.add(surnameTextField);
        }
        // Add labels and text fields to the city panel
        {
            cityPanel.add(cityLabel);
            cityPanel.add(cityTextField);
        }

        // Create the buttons
        {
            addButton = new JButton("Add");
            showAllButton = new JButton("Show All");

            addButton.setFocusable(false);
            showAllButton.setFocusable(false);
        }
        // Add buttons to the buttons panel
        {
            buttonsPanel.add(addButton);
            buttonsPanel.add(showAllButton);
        }
        // Add panels to the main frame
        {
            add(namePanel);
            add(surnamePanel);
            add(genderPanel);
            add(agePanel);
            add(cityPanel);
            add(countryPanel);
            add(buttonsPanel);
        }
        // Set up action listeners for the buttons & age scrollbar
        {
            addButton.addActionListener(new ActionListener() {

                // handling click event on add button, then write the data of the person to the
                // arraylist of people
                public void actionPerformed(ActionEvent e) {

                    people.add(new Person(nameTextField.getText(), surnameTextField.getText(),
                            genderGroup.getSelection().getActionCommand(),
                            Integer.parseInt(ageTextField.getText()),
                            cityTextField.getText(), countryComboBox.getSelectedItem().toString()));

                    nameTextField.setText("");
                    surnameTextField.setText("");
                    ageTextField.setText("0");
                    cityTextField.setText("");
                    genderGroup.clearSelection();
                    countryComboBox.setSelectedIndex(0);
                    ageScrollBar.setValue(0);
                }

            });

            showAllButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    // If a table is already showing, don't show another one
                    if (showingAll) {
                        return;
                    }

                    // Deserializing the person objects from the file
                    displayPeopleData();
                }
            });

            // Scrollbar will change the value of the age in the text field
            ageScrollBar.addAdjustmentListener(new AdjustmentListener() {
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    ageTextField.setText(Integer.toString(ageScrollBar.getValue()));
                }
            });

            // Text field will change the value of the scrollbar
            ageTextField.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    try {
                        int age = Integer.parseInt(ageTextField.getText());

                        ageScrollBar.setValue(age);
                    } catch (NumberFormatException ex) {
                        ageScrollBar.setValue(0);
                    }
                }
            });
        }

        // Set up the main frame
        {
            // When the frame gets closed, close the oos and close the whole program, and we
            // save the new arraylist to the file
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {

                    // Open the fos & oos & write the arraylist to the file:
                    try {
                        fos = new FileOutputStream(peopleDataPath);
                        oos = new ObjectOutputStream(fos);

                        oos.writeObject(people);
                        oos.close();

                    } catch (IOException excep) {
                        excep.printStackTrace();
                    } finally {
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
            });

            setTitle("Person Form");
            setLayout(new GridLayout(7, 1));
            // Pack and display the frame
            setMinimumSize(new Dimension(400, 400));
            setVisible(true);
        }

    }

    // Read the people's data from the file & delete the old file
    public ArrayList<Person> readPeopleData() {

        ArrayList<Person> people = new ArrayList<>();

        FileInputStream fis;
        try {

            System.out.println("Reading the people from people.dat...");

            fis = new FileInputStream(peopleDataPath);

            ObjectInputStream ois = new ObjectInputStream(fis);

            people = (ArrayList<Person>) ois.readObject();

            System.out.println("Printing the read people from people.dat:\n" + people);

            ois.close();
            fis.close();

        } catch (IOException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {

            return new ArrayList<>();

        } finally {
            File file = new File(peopleDataPath);
            if (file.exists()) {
                file.delete();
            }
        }
        return people;

    }

    public void displayPeopleData() {

        // So we don't open multiple tables
        showingAll = true;

        JFrame frame = new JFrame("People's Data"); // Create a JFrame with the title "People's Data"
        frame.setMinimumSize(new Dimension(500, 500)); // Set the minimum size of the frame to 500x500

        // Create a label as the header
        JLabel headerLabel = new JLabel("People's Data");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font and size of the label
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label

        // Create a JTable with columns corresponding to the fields of the Person class
        String[] columnNames = { "Order", "Name", "Surname", "Gender", "Age", "City", "Country" };
        Object[][] rowData = new Object[people.size()][7];
        for (int i = 0; i < people.size(); i++) {
            Person tempPerson = people.get(i);
            rowData[i][0] = i + 1; // Set the order of the person in the file
            rowData[i][1] = tempPerson.getName();
            rowData[i][2] = tempPerson.getSurname();
            rowData[i][3] = tempPerson.getGender();
            rowData[i][4] = tempPerson.getAge();
            rowData[i][5] = tempPerson.getCity();
            rowData[i][6] = tempPerson.getCountry();
        }

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };

        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false); // Make the table columns unmovable

        JScrollPane scrollPane = new JScrollPane(table); // Add the table to a scroll pane
        frame.getContentPane().add(headerLabel, BorderLayout.NORTH); // Add the header label to the frame's content pane
                                                                     // at the top
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the frame's content pane
                                                                     // at the center

        frame.pack(); // Pack the components
        frame.setVisible(true); // Make the frame visible

        // Detect when the frame gets closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showingAll = false;
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }

    public static void main(String[] args) {

        new PersonForm();
    }
}