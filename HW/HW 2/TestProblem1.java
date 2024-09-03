public class TestProblem1 {

    public static void main(String[] args) {

        GeometricalForm[] forms = { new Elipse(1, 1, 1, 1), new Rectangle(2, 2, 2, 2), new GeometricalForm(3, 3) };

        for (GeometricalForm form : forms) {

            System.out.println(form.toString());

            form.move(0, 0);
            form.changeFormColor("Red");
            form.changeInnerColor("Blue");
            form.enlarge();
        }

    }
}
