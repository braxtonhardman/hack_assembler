
//Code Module
//Ethan Emerson, Braxton Hardman, Christian Sapp
import java.util.HashMap;

public class Code {

	// Hashmap for comp. jump, and dest consisting of key: string and value: string 
	private HashMap<String, String> compCodes;
	private HashMap<String, String> jumpCodes;
	private HashMap<String, String> destCodes;

	// Constructory initalizes all the tables 
	public Code() {
		this.putCompCodes();
		this.putJumpCodes();
		this.putDestCodes();
	}

	// Put all the predefined comp field codes 
	private void putCompCodes() {
		this.compCodes = new HashMap<>();
		this.compCodes.put("0", "0101010");
		this.compCodes.put("1", "0111111");
		this.compCodes.put("-1", "0111010");
		this.compCodes.put("D", "0001100");
		this.compCodes.put("A", "0110000");
		this.compCodes.put("!D", "0001101");
		this.compCodes.put("!A", "0110001");
		this.compCodes.put("-D", "0001111");
		this.compCodes.put("-A", "0110011");
		this.compCodes.put("D+1", "0011111");
		this.compCodes.put("A+1", "0110111");
		this.compCodes.put("D-1", "0001110");
		this.compCodes.put("A-1", "0110010");
		this.compCodes.put("D+A", "0000010");
		this.compCodes.put("D-A", "0010011");
		this.compCodes.put("A-D", "0000111");
		this.compCodes.put("D&A", "0000000");
		this.compCodes.put("D|A", "0010101");
		this.compCodes.put("M", "1110000");
		this.compCodes.put("!M", "1110001");
		this.compCodes.put("-M", "1110011");
		this.compCodes.put("M+1", "1110111");
		this.compCodes.put("M-1", "1110010");
		this.compCodes.put("D+M", "1000010");
		this.compCodes.put("D-M", "1010011");
		this.compCodes.put("M-D", "1000111");
		this.compCodes.put("D&M", "1000000");
		this.compCodes.put("D|M", "1010101");
	}

	// Put all the predefined jump codes 
	private void putJumpCodes() {
		this.jumpCodes = new HashMap<String, String>();
		this.jumpCodes.put("NULL", "000");
		this.jumpCodes.put("JGT", "001");
		this.jumpCodes.put("JEQ", "010");
		this.jumpCodes.put("JGE", "011");
		this.jumpCodes.put("JLT", "100");
		this.jumpCodes.put("JNE", "101");
		this.jumpCodes.put("JLE", "110");
		this.jumpCodes.put("JMP", "111");
	}

	// Put all the predefined dest codes 
	private void putDestCodes() {
		this.destCodes = new HashMap<String, String>();
		this.destCodes.put("NULL", "000");
		this.destCodes.put("M", "001");
		this.destCodes.put("D", "010");
		this.destCodes.put("MD", "011");
		this.destCodes.put("A", "100");
		this.destCodes.put("AM", "101");
		this.destCodes.put("AD", "110");
		this.destCodes.put("AMD", "111");

	}

	// Get given the key the corresponding comp code 
	public String comp(String digits) {
		return this.compCodes.get(digits);
	}

	// Get given the key the corresponding dest code 
	public String dest(String digits) {
		return this.destCodes.get(digits);
	}

	// Get given the key the corresponding jump code 
	public String jump(String digits) {
		return this.jumpCodes.get(digits);
	}
}