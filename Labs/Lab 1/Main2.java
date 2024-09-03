

public class Main2 {

	public static void main(String[] args) {
		
		Computer pc = new Computer(8, "i7");
		SoftwareProduct sp = new SoftwareProduct("Nvidia Graphics", "i7", 4, "Desktop");
		
		System.out.println(pc.addProduct(sp));
	}
}
