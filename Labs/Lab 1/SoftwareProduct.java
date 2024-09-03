

public class SoftwareProduct {

	private String name;
	private String minCPU;
	private int minRAM;
	private String platform;
	
	SoftwareProduct(String n, String mCPU,int mRAM, String plat ){
		this.name = n;
		this.minCPU = mCPU;
		this.minRAM = mRAM;
		this.platform = plat;
	}
	
	public boolean checkCompatibility(Computer c) {
			
		return c.getCPU().compareTo(this.minCPU) >= 0 && c.getRAM() >= this.minRAM;
	}
	
}
