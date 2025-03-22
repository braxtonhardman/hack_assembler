//Java Assembler Program
//By Ethan Emerson, Braxton Hardman, Christian Sapp
import java.lang.StringBuilder;

public class Assembler
{
    private static Parser parser = null;
    private static SymbolTable symbolTable = null;
    
        public static void main(String []args) //main method that runs the program
        {
            //Grab data from args and initialize the parser
            parser = new Parser(args[0]);
            symbolTable = new SymbolTable(); 
            FirstPass();
        }

    // First Pass Creates the Symbol Table 
    private static void FirstPass() { 
        int lineNumber = 0; 
        while(parser.hasMoreLines()) { 
            parser.advance();
            char instructionType = parser.getInstructionType();
            if(instructionType == 'l') { 
                symbolTable.addEntry(parser.getSymbol(), lineNumber);
            } else if(instructionType == 'a') { 
                symbolTable.addEntry(parser.getSymbol(), lineNumber);
            } else { 
                lineNumber++;
                return;
            }
            lineNumber++; 
        }
    }

    // Second Pass does the translation
    private void SecondPass() { 

    }

	private static String decimalToBinary(int number) {

        int num = number;
        StringBuilder sb = new StringBuilder();

        // Calculate binary digits
        while (num != 0) {
            int remainder = num % 2;
            sb.insert(0, remainder);
            num = num / 2;
        }

        // Add leading zeros to make it 16 bits long
        while (sb.length() < 16) {
            sb.insert(0, '0');
        }

        return sb.toString(); 
    }
}