//Java Assembler Program
//By Ethan Emerson, Braxton Hardman, Christian Sapp
import java.util.HashMap;

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
        int lineNumber = 1; 
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
}