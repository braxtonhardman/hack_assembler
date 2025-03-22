//Java Assembler Program
//By Ethan Emerson, Braxton Hardman, Christian Sapp
import java.util.HashMap;

public class Assembler
{
    private Parser  parser = null;

    public static void main(String []args) //main method that runs the program
    {
        parser = new Parser(); 
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