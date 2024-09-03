public class Elipse extends GeometricalForm {

    private int hRadius;
    private int vRadius;

    Elipse(int x, int y, int vr, int hr) {
        super(x, y);
        this.hRadius = hr;
        this.vRadius = vr;
    }

    public int getHorizRadius() {
        return this.hRadius;
    }

    public int getVertRadius() {
        return this.vRadius;
    }

    public void enlarge() {

        this.hRadius += 1;
        this.vRadius += 1;
    }

    public void shrink() {

        this.hRadius -= 1;
        this.vRadius -= 1;
    }

}