public class Book {

    private String title;
    private String author;
    private String genre;
    private Long ISBN;
    private String shortDesc;

    Book(String t, String a, String g, Long i, String s) {

        title = t;
        author = a;
        genre = g;
        ISBN = i;
        shortDesc = s;
    }

    public String toString() {
        return "\n" + title + "," + author + "," + genre + "," + ISBN + "," + shortDesc;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Long getISBN() {
        return ISBN;
    }

    public String getDesc() {
        return shortDesc;
    }
}