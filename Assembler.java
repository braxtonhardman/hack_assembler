//Java Assembler Program
//By Ethan Emerson, Braxton Hardman, Christian Sapp
import java.util.HashMap;

public class Assembler
{
    private static Parser parser = null;
    
        public static void main(String []args) //main method that runs the program
        {
            //Grab data from args and initialize the parser
            parser = new Parser(args[0]);
        }

    // First Pass Creates the Symbol Table 
    private void FirstPass() { 
        //Grab all the symbols. 
        while(parser.hasNextLine()) { 
            line = parser.next(); 
        }
    }

    // Second Pass does the translation
    private void SecondPass() { 

    }

    private void typeCommand(String line) { 

    }
}