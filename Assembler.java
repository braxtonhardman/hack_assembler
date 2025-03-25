//Java Assembler Program
//By Ethan Emerson, Braxton Hardman, Christian Sapp
import java.lang.StringBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class Assembler
{
    private static Parser parser = null;
    private static SymbolTable symbolTable = null;
    private static String fileName;
    private static Code code = null; 
    private static FileWriter fileWriter = null;
    
        public static void main(String []args) //main method that runs the program
        {
            //Grab data from args and initialize the parser
            fileName = args[0];
            try { 
                fileWriter = new FileWriter(fileName + ".hack");
            } catch (Exception e) { 
                e.printStackTrace();
                System.exit(0);
            }

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
            if(instructionType == 'c') { //We now build out the binary string representation of the c instruction
                String dest = parser.getDest() != null ? code.dest(parser.getDest()) : "000";
                String comp = parser.getComp() != null ? code.comp(parser.getComp()) : "000000";
                String jump = parser.getjump() != null ? code.jump(parser.getjump()) : "000";
                
                
                String decimal = "111" + comp + dest + jump;
                try { 
                    fileWriter.write(decimal + "\n");
                } catch(IOException e) { 
                    e.printStackTrace();
                    System.exit(0);
                }
                
            } else if(instructionType == 'a') { 
                // Check if the symbol exists in the table 
                // if it does not exist than it is a number
                if(symbolTable.contains(parser.getSymbol())) { 
                    //Symbol is in the table look up the symbol and conver to binary 
                   String binary = decimalToBinary(symbolTable.getAddress(parser.getSymbol()));
                   try { 
                    fileWriter.write(binary + "\n");
                   } catch(IOException e) { 
                    e.printStackTrace();
                    System.exit(0);
                   }
                   
                } else { 

                    // Convert decimal numbers of line locations to binary 
                    String binary = decimalToBinary(Integer.parseInt(parser.getSymbol()));
                    try { 
                        fileWriter.write(binary + "\n");
                    } catch(IOException e) { 
                        e.printStackTrace();
                        System.exit(0);
                    }
                } 

            }

        }
        try { 
            fileWriter.close();
        } catch(IOException e) { 
            e.printStackTrace();
            System.exit(0);
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