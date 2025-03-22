import java.util.HashMap;



public class SymbolTable {



	private HashMap<String, Integer> symbolTable;

	private String ALL_VALID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.$:";

	private String INITIAL_VALID_CHARS = "ABCDEFGHIKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_.$:";

	

	

	//DESCRIPTION: initializes hashmap with predefined symbols

	//PRECONDITION: follows symbols/values from book/appendix

	//POSTCONDITION: all hashmap values have valid address integer

	public SymbolTable() {

		symbolTable = new HashMap<>();
		this.symbolTable.put("SP", Integer.valueOf(0));
		this.symbolTable.put("LCL", Integer.valueOf(1));
		this.symbolTable.put("ARG", Integer.valueOf(2));
		this.symbolTable.put("THIS", Integer.valueOf(3));
		this.symbolTable.put("THAT", Integer.valueOf(4));
		this.symbolTable.put("R0", Integer.valueOf(0));
		this.symbolTable.put("R1", Integer.valueOf(1));
		this.symbolTable.put("R2", Integer.valueOf(2));
		this.symbolTable.put("R3", Integer.valueOf(3));
		this.symbolTable.put("R4", Integer.valueOf(4));
		this.symbolTable.put("R5", Integer.valueOf(5));
		this.symbolTable.put("R6", Integer.valueOf(6));
		this.symbolTable.put("R7", Integer.valueOf(7));
		this.symbolTable.put("R8", Integer.valueOf(8));
		this.symbolTable.put("R9", Integer.valueOf(9));
		this.symbolTable.put("R10", Integer.valueOf(10));
		this.symbolTable.put("R11", Integer.valueOf(11));
		this.symbolTable.put("R12", Integer.valueOf(12));
		this.symbolTable.put("R13", Integer.valueOf(13));
		this.symbolTable.put("R14", Integer.valueOf(14));
		this.symbolTable.put("R15", Integer.valueOf(15));
		this.symbolTable.put("SCREEN", Integer.valueOf(16384));
		this.symbolTable.put("KBD", Integer.valueOf(24576));
	}

	
	public boolean addEntry(String symbol, int address) {

		if(!this.validName(symbol)) {
			return false;
		}

		

		if(!this.contains(symbol)) {

			if(address < 24576 && address >= 0) {

				if(this.symbolTable.putIfAbsent(symbol, address) != null) {
					return false;
				} else {
					this.symbolTable.put(symbol, address);
					return true;
				}

			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean contains(String symbol) {
		return symbolTable.containsKey(symbol);

	}

	public int getAddress(String symbol) {
		return this.symbolTable.get(symbol);
	}


	public boolean validName(String symbol) {
		boolean valid = true;

		if(valid==true) {

			for(int j = 0; j < INITIAL_VALID_CHARS.length(); j++) {
				String currentChar = Character.toString(this.INITIAL_VALID_CHARS.charAt(j));
				String firstChar = Character.toString(symbol.charAt(0));

				if(firstChar.contains(currentChar)) {
					valid = true;
					break;
				} else {
					valid = false;
				}
			}
		}

		if(valid==true) {
			for(int k = 0; k < symbol.length(); k++) {
				String currentChar = Character.toString(symbol.charAt(k));
				if(this.ALL_VALID_CHARS.contains(currentChar)) {
					valid = true;
				} else {
					valid = false;
					break;
				}
			}
		}
		
		return valid;
    }
}