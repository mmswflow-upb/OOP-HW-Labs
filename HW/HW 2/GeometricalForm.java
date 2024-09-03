public class GeometricalForm implements FormOperations {

    private int xCoord;
    private int yCoord;
    private String iColor;
    private String fColor;

    GeometricalForm(int x, int y) {

        this.xCoord = x;
        this.yCoord = y;
    }

    public String toString() {
        return "X: " + this.getXCoordinate() + " | Y: " + this.getYCoordinate() + "\nInner Color: "
                + this.getInnerColor() + " | Outer Color: " + this.getFormColor() + "\n\n";
    }

    public int getXCoordinate() {
        return this.xCoord;
    }

    public int getYCoordinate() {
        return this.yCoord;
    }

    public String getInnerColor() {
        return this.iColor;
    }

    public String getFormColor() {
        return this.fColor;
    }

    public void move(int x, int y) {

        this.xCoord = x;
        this.yCoord = y;
    }

    public void shrink() {
        // override it inside subclasses
    }

    public void enlarge() {
        // override it inside subclasses
    }

    public void changeInnerColor(String color) {

        this.iColor = color;
    }

    public void changeFormColor(String color) {

        this.fColor = color;
    }
}