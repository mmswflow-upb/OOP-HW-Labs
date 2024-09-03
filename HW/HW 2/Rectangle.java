public class Rectangle extends GeometricalForm {

    private int width;
    private int height;

    Rectangle(int x, int y, int w, int h) {
        super(x, y);
        this.width = w;
        this.height = h;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void enlarge() {

        this.width += 1;
        this.height += 1;
    }

    public void shrink() {

        this.width -= 1;
        this.height -= 1;
    }
}
