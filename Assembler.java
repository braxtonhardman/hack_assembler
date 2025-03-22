//Java Assembler Program
//By Ethan Emerson, Braxton Hardman, Christian Sapp
import java.util.HashMap;

public class Assembler
{
    private Parser  parser = null;
    private HashMap<String, Integer> symbolTable = null; 
    public static void main(String []args) //main method that runs the program
    {
        parser = new Parser(); 
    }

    // First Pass Creates the Symbol Table 
    private void FirstPass() { 

    }

    // Second Pass does the translation
    private void SecondPass() { 

    }

    // Initialize the symbol table with the predefined values 
    private void InitSymbolTable() { 
        symbolTable = new HashMap<>();

    }


}