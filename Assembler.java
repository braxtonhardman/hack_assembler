//Java Assembler Program
//By Ethan Emerson, Braxton Hardman, Christian Sapp
import java.lang.StringBuilder;

public class Assembler
{
    private static Parser parser = null;
    private static SymbolTable symbolTable = null;
    private static String fileName;
    private static Code code = null; 
    
        public static void main(String []args) //main method that runs the program
        {
            //Grab data from args and initialize the parser
            fileName = args[0];
            parser = new Parser(fileName);
            symbolTable = new SymbolTable(); 
            FirstPass();
            SecondPass();
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
    private static void SecondPass() { 

        //Create a new parser
        parser = new Parser(fileName);
        int lineNumber = 0;
        while(parser.hasMoreLines()){
            parser.advance();
            char instructionType = parser.getInstructionType();
            if(instructionType == 'c') { 
                String comp = parser.getComp();
                // If the command is a C command 
                if(comp.contains(";")) { 

                }
                String[] computations = comp.split("=");
                
            } else if(instructionType == 'a') { 

            } else { 

            }

            //Now do different things based on an A and C instruction
            
        }
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