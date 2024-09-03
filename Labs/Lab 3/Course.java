public final class Course {

    private final String name;
    private final int creditPoints;
    private final Type type;
    private final Stream stream;

    Course(Type t, Stream s, String n, int points) {

        this.name = n;
        this.creditPoints = points;
        this.type = t;
        this.stream = s;
    }

    enum Type {
        Fundamental, Specialization, Discipline
    };

    enum Stream {
        English, French, German
    };

    public Type getType() {
        return this.type;
    }

    public Stream getStream() {
        return this.stream;
    }

    public int getPoints() {
        return this.creditPoints;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Course: Name: " + this.name + " | Type-" + this.type + " | Stream: " + this.stream
                + " | Credit Points: " + this.creditPoints + "\n";
    }
}
