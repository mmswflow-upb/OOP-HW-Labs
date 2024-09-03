import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Library extends JFrame {

    private static final String booksTableFilePath = "BooksTable.csv";
    private static final ArrayList<Book> storedBooksList = new ArrayList<>();

    Library() {

        // Creating Menu UI
        JPanel menuButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel menuAppTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel menuAppTitle = new JLabel("My Java Library");
        menuAppTitlePanel.add(menuAppTitle);

        JButton openBookForm = new JButton("Add Books");
        JButton openBooksViewButton = new JButton("View All Books");

        menuButtonsPanel.add(openBookForm);
        menuButtonsPanel.add(openBooksViewButton);

        // Creating the Book form UI

        JPanel bookFormPanel = new JPanel(new GridLayout(3, 1, 10, 20));
        bookFormPanel.setVisible(false);

        JPanel bookFormButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bookFormButtonsPanel.setVisible(false);

        JPanel titleFormPanel = new JPanel(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(10);
        titleFormPanel.add(titleLabel);
        titleFormPanel.add(titleField);

        JPanel authorFormPanel = new JPanel(new GridLayout(2, 1));
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField(10);
        authorFormPanel.add(authorLabel);
        authorFormPanel.add(authorField);

        JPanel genreFormPanel = new JPanel(new GridLayout(2, 1));
        String[] genres = { "Fiction", "Non-Fiction", "Science Fiction", "Fantasy", "Mystery",
                "Thriller", "Romance", "Historical", "Biography", "Autobiography", "Self-Help",
                "Health & Wellness", "Cookbook", "Science & Technology", "Travel",
                "Young Adult", "Children's Literature", "Horror", "Poetry",
                "Graphic Novel", "Classic Literature", "Adventure",
                "True Crime", "Religious & Spiritual", "Educational"
        };
        JLabel genreLabel = new JLabel("Genre:");
        JComboBox genreComboBox = new JComboBox(genres);
        genreFormPanel.add(genreLabel);
        genreFormPanel.add(genreComboBox);

        JPanel isbnFormPanel = new JPanel(new GridLayout(2, 1));
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField(10);
        isbnFormPanel.add(isbnLabel);
        isbnFormPanel.add(isbnField);

        JPanel descFormPanel = new JPanel(new GridLayout(2, 1));
        JLabel descLabel = new JLabel("Short Description: ");
        JTextArea descTextArea = new JTextArea(10, 20);
        JScrollPane descScrollableTextArea = new JScrollPane(descTextArea);
        descFormPanel.add(descLabel);
        descFormPanel.add(descScrollableTextArea);

        bookFormPanel.add(titleFormPanel);
        bookFormPanel.add(authorFormPanel);
        bookFormPanel.add(genreFormPanel);
        bookFormPanel.add(isbnFormPanel);
        bookFormPanel.add(descFormPanel);

        JButton addBookFormButton = new JButton("Add Book");
        JButton menuBookFormButton = new JButton("Menu");
        bookFormButtonsPanel.add(addBookFormButton);
        bookFormButtonsPanel.add(menuBookFormButton);

        // Creating the View All Books UI

        JPanel booksViewPanel = new JPanel(new GridLayout(3, 1));
        booksViewPanel.setVisible(false);

        JPanel searchBarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel searchBarLabel = new JLabel("Search by name or author:");
        JTextField searchBarField = new JTextField(20);

        String[] filters = { "Both", "Title", "Author" };
        JLabel filterLabel = new JLabel("Search By:");
        JComboBox filterComboBox = new JComboBox<>(filters);

        searchBarPanel.add(searchBarLabel);
        searchBarPanel.add(searchBarField);
        searchBarPanel.add(filterLabel);
        searchBarPanel.add(filterComboBox);

        DefaultTableModel dfm = new DefaultTableModel() {
            // We shouldn't be able to edit the ISBN
            public boolean isCellEditable(int row, int column) {

                return column != 0;
            }
        };

        dfm.addColumn("ISBN");
        dfm.addColumn("Title");
        dfm.addColumn("Author");
        dfm.addColumn("Genre");
        dfm.addColumn("Short Description");

        JTable resultsTable = new JTable(dfm);
        resultsTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane[] resultsScrollPane = { new JScrollPane(resultsTable) };

        JPanel booksViewButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton booksViewSaveChanges = new JButton("Save Changes");
        JButton booksViewMenuButton = new JButton("Menu");
        booksViewButtonsPanel.add(booksViewSaveChanges);
        booksViewButtonsPanel.add(booksViewMenuButton);

        booksViewPanel.add(searchBarPanel);
        booksViewPanel.add(resultsScrollPane[0]);
        booksViewPanel.add(booksViewButtonsPanel);

        // Setting up the Layout of the Frame:

        setMinimumSize(new Dimension(600, 600));
        setLayout(new GridLayout(3, 1));
        setLocationRelativeTo(null);

        // Adding UI elements to main menu frame:
        add(menuAppTitlePanel);
        add(menuButtonsPanel);

        // Setting up the fonts:
        openBooksViewButton.setFont(new Font("Courier New", Font.PLAIN, 20));
        openBookForm.setFont(new Font("Courier New", Font.PLAIN, 20));
        menuAppTitle.setFont(new Font("Monospace", Font.ITALIC, 30));

        // Removing extra decorations of UI
        menuAppTitle.setFocusable(false);
        openBookForm.setFocusable(false);
        openBooksViewButton.setFocusable(false);
        addBookFormButton.setFocusable(false);
        menuBookFormButton.setFocusable(false);
        booksViewMenuButton.setFocusable(false);
        booksViewSaveChanges.setFocusable(false);

        resultsScrollPane[0].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultsScrollPane[0].setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Make it Stop running the app when exiting window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the list of books gathered from previous sessions
        updateBooksList();

        // Make window visible
        setTitle("Library");
        setLocationRelativeTo(null);
        setVisible(true);

        // Navigation Buttons:

        openBookForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                // We hide the menu

                menuAppTitlePanel.setVisible(false);
                menuButtonsPanel.setVisible(false);
                remove(menuAppTitlePanel);
                remove(menuButtonsPanel);

                // We make the book form visible

                add(bookFormPanel);
                add(bookFormButtonsPanel);

                bookFormPanel.setVisible(true);
                bookFormButtonsPanel.setVisible(true);

            }
        });

        menuBookFormButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                // We close the book form UI
                remove(bookFormPanel);
                remove(bookFormButtonsPanel);

                bookFormPanel.setVisible(false);
                bookFormButtonsPanel.setVisible(false);

                // We make the menu visible again

                add(menuAppTitlePanel);
                add(menuButtonsPanel);

                menuAppTitlePanel.setVisible(true);
                menuButtonsPanel.setVisible(true);

            }
        });

        openBooksViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                // We hide the menu

                menuAppTitlePanel.setVisible(false);
                menuButtonsPanel.setVisible(false);

                remove(menuAppTitlePanel);
                remove(menuButtonsPanel);

                // We make the book form visible
                add(booksViewPanel);

                booksViewPanel.setVisible(true);

            }
        });

        booksViewMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                // We close the book form UI

                remove(booksViewPanel);

                booksViewPanel.setVisible(false);

                // We make the menu visible again

                add(menuAppTitlePanel);
                add(menuButtonsPanel);

                menuAppTitlePanel.setVisible(true);
                menuButtonsPanel.setVisible(true);

            }
        });

        // Functional Buttons (Saving ,Editing,Searching)

        // Adding new books into the library
        addBookFormButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                BufferedWriter bw;

                try {

                    Book newBook = new Book(titleField.getText(), authorField.getText(),
                            genreComboBox.getSelectedItem().toString(), Long.valueOf(isbnField.getText()),
                            descTextArea.getText());

                    // Write in the csv file the next instance of a book
                    bw = new BufferedWriter(new FileWriter(booksTableFilePath, true));

                    bw.write(newBook.toString());

                    bw.close();
                } catch (IOException exec) {

                    exec.printStackTrace();

                } finally {

                    titleField.setText("");
                    authorField.setText("");
                    genreComboBox.setSelectedItem(null);
                    isbnField.setText("");
                    descTextArea.setText("");

                    updateBooksList();
                }
            }
        });

        // Searching through the library
        searchBarField.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                updateBooksList();
                textChanged(e);
            }

            public void removeUpdate(DocumentEvent e) {
                updateBooksList();
                textChanged(e);

            }

            public void changedUpdate(DocumentEvent e) {
                updateBooksList();
                textChanged(e);
            }

            // Handle Changes to text (Live Searching)
            private void textChanged(DocumentEvent e) {

                String searchText = searchBarField.getText();

                // Remove old rows
                dfm.setRowCount(0);

                ArrayList<Book> searchResultBooksList = new ArrayList<>(storedBooksList);

                // Check if the search bar is empty, if it is we're going to give all the
                // results
                if (searchText.equals("")) {

                    searchResultBooksList = (ArrayList<Book>) searchResultBooksList.stream().distinct()
                            .collect(Collectors.toList());
                } else {
                    // If searchbox isn't empty, we start looking for a match in our list of books
                    if (filterComboBox.getSelectedItem().toString().equals("Both")) {

                        searchResultBooksList = (ArrayList<Book>) searchResultBooksList.stream()
                                .filter(b -> b.getTitle().contains(searchText) || b.getAuthor().contains(searchText))
                                .distinct()
                                .collect(Collectors.toList());

                    } else if (filterComboBox.getSelectedItem().toString().equals("Title")) {

                        searchResultBooksList = (ArrayList<Book>) searchResultBooksList.stream().distinct()
                                .filter(b -> b.getTitle().contains(searchText)).distinct()
                                .collect(Collectors.toList());

                    } else {

                        searchResultBooksList = (ArrayList<Book>) searchResultBooksList.stream()
                                .filter(b -> b.getAuthor().contains(searchText))
                                .collect(Collectors.toList());
                    }
                }

                // Adding the results into the table

                for (Book b : searchResultBooksList) {

                    dfm.addRow(new Object[] { String.valueOf(b.getISBN()), b.getTitle(), b.getAuthor(), b.getGenre(),
                            b.getDesc() });
                }
            }

        });

        // Changing search filters will change the results
        filterComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ev) {

            }
        });

        // Saving changes made to books from the books view
        booksViewSaveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                for (int i = 0; i < dfm.getRowCount(); i++) {

                    Long tempISBN = Long.valueOf((String) dfm.getValueAt(i, 0));
                    String tempTitle = (String) dfm.getValueAt(i, 1);
                    String tempAuthor = (String) dfm.getValueAt(i, 2);
                    String tempGenre = (String) dfm.getValueAt(i, 3);
                    String tempDesc = (String) dfm.getValueAt(i, 4);
                    Book tempBook = new Book(tempTitle, tempAuthor, tempGenre, tempISBN, tempDesc);

                    int indx = -1;

                    for (Book b : storedBooksList) {

                        if (b.getISBN().toString().equals(tempISBN.toString())) {

                            indx = storedBooksList.indexOf(b);
                        }
                    }

                    if (indx != -1) {

                        storedBooksList.set(indx, tempBook);
                    } else {

                        return;
                    }

                }

                BufferedWriter bw;

                try {

                    bw = new BufferedWriter(new FileWriter(booksTableFilePath, false));

                    bw.write("Title,Author,Genre,ISBN,Short Description");

                    for (Book b : storedBooksList) {
                        bw.write(b.toString());
                    }

                    bw.close();

                } catch (IOException excep) {

                    excep.printStackTrace();
                }
            }
        });

    }

    public void updateBooksList() {

        BufferedReader br;

        try {

            br = new BufferedReader(new FileReader(booksTableFilePath));

            storedBooksList.clear();

            String line = "";

            // Skip the first line
            br.readLine();
            while ((line = br.readLine()) != null) {

                if (line.equals("")) {
                    continue;
                }

                String[] data = line.split(",");

                Book tempBook = new Book(data[0], data[1], data[2], Long.valueOf(data[3]), data[4]);
                storedBooksList.add(tempBook);
            }

            br.close();

        } catch (IOException exec) {

            exec.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Start app
        new Library();
    }
}