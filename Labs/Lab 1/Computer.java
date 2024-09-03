

public class Computer {

	private int ram;
	private String cpu;
	private SoftwareProduct soft_prod;
	
	Computer(int newRam, String newCpu){
	
			if (newRam == 4 || newRam == 8 || newRam == 16 || newRam == 32) {
				if (newCpu.equals("i3") || newCpu.equals("i5") || newCpu.equals("i7")) {
					this.cpu = newCpu;
					this.ram = newRam;
				}else {
					throw new IllegalArgumentException("BAD CPU");
				}
			}else {
				throw new IllegalArgumentException("BAD RAM");
			}
	}
	
	public int getRAM() {
		return this.ram;
	}
	
	public String getCPU() {
		return this.cpu;
	}
	
	public boolean addProduct(SoftwareProduct sp) {
		
		if (sp.checkCompatibility(this)) {
			
			this.soft_prod = sp;
			return true;
		}
		
		return false;
	}
}
