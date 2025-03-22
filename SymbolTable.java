import java.util.HashMap;



public class SymbolTable {



	private HashMap<String, Integer> symbolTable;
    // All valid characters of 
	private String ALL_VALID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.$:";
    // These are valid inital characters
	private String INITIAL_VALID_CHARS = "ABCDEFGHIKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_.$:";
	
	//DESCRIPTION: initializes hashmap with predefined symbols
	//PRECONDITION: follows symbols/values from book/appendix
	//POSTCONDITION: all hashmap values have valid address integer

	public SymbolTable() {
		symbolTable = new HashMap<>();

        // All the initial values that are predefined symbols. 
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

	
    // Add value to the symbol table being the symbol and the address which would be the line number
	public boolean addEntry(String symbol, int address) {

        // If the name is not valid return false 
		if(!this.validName(symbol)) {
			return false;
		}

	
        // If the symbol does not already exist in the table 
		if(!this.contains(symbol)) {

            // Make sure address is valid and not in the screen 
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


    // Check if the symbol is a valid name 
	public boolean validName(String symbol) {
		boolean valid = true;

		if(valid==true) {

            // Compare the first letter of the symbol to all the intial valid characters 
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
            // Check if the rest of the characters in the symbol are valid
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