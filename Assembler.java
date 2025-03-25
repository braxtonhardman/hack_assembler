//Java Assembler Program
//By Ethan Emerson, Braxton Hardman, Christian Sapp
import java.lang.StringBuilder;
import java.io.FileWriter;

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
            code = new Code(); 
            FirstPass();
            SecondPass();
        }

    // First Pass Creates the Symbol Table 
    private static void FirstPass() { 
        int lineNumber = 0; 
        int varCounter = 16;
        while(parser.hasMoreLines()) { 
            parser.advance();
            char instructionType = parser.getInstructionType();
            if(instructionType == 'l') { 
                symbolTable.addEntry(parser.getSymbol(), lineNumber);
            } else if(instructionType == 'a') { 
                
                symbolTable.addEntry(parser.getSymbol(), varCounter);
                varCounter++; 
                
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
                // If the command is a jump command split on the ; else split on the = 
                if(parser.getjump() != null) { 
                    String dest = parser.getDest(); 
                    String jump = parser.getjump(); 
                    System.out.println(dest + " " + jump);
                } else { 
                    String dest = parser.getDest(); 
                    String comp = parser.getComp(); 
                    System.out.println(dest + " " + comp);
                }
                
            } else if(instructionType == 'a') { 
                // Check if the symbol exists in the table 
                // if it does not exist than it is a number
                if(symbolTable.contains(parser.getSymbol())) { 
                    //Symbol is in the table look up the symbol and conver to binary 
                   String binary = decimalToBinary(symbolTable.getAddress(parser.getSymbol()));
                   System.out.println(binary);
                } else { 

                    // Convert decimal numbers of line locations to binary 
                    String binary = decimalToBinary(Integer.parseInt(parser.getSymbol()));
                    System.out.println(binary);
                }

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