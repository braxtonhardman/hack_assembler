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
                //print out the first three bits of the c_instruction
                System.out.print("111");

                //Now check and see what the comp value is
                String compString = parser.getComp();
                if(compString != null){
                    
                    //Now run through all the different domains of possible combinations for the comp section
                    if(compString.equals("0")) //0
                    {
                        System.out.print("0101010");
                    }
                    else if(compString.equals("1")) //1
                    {
                        System.out.print("0111111");
                    }
                    else if(compString.equals("-1"))//-1
                    {
                        System.out.println("0111010");
                    }
                    else if(compString.equals("D")) //D
                    {
                        System.out.print("0001100");
                    }
                    else if(compString.equals("A")) //A
                    {
                        System.out.print("0110000");
                    }
                    else if(compString.equals("M")) //M
                    {
                        System.out.print("1110000");
                    }
                    else if(compString.equals("!D")) //!D
                    {
                        System.out.print("0001101");
                    }
                    else if(compString.equals("!A")) //!A
                    {
                        System.out.print("0110001");
                    }
                    else if(compString.equals("A")) //!M
                    {
                        System.out.print("1110001");
                    }
                    else if(compString.equals("-D")) //-D
                    {
                        System.out.print("0001111");
                    }
                    else if(compString.equals("-A")) //-A
                    {
                        System.out.print("0110011");
                    }
                    else if(compString.equals("-M")) //-M
                    {
                        System.out.print("1110011");
                    }
                    else if(compString.equals("D+1") || compString.equals("1+D")) //D+1
                    {
                        System.out.print("0011111");
                    }
                    else if(compString.equals("A+1") || compString.equals("1+A")) //A+1
                    {
                        System.out.print("0110111");
                    }
                    else if(compString.equals("M+1") || compString.equals("1+M")) //M+1
                    {
                        System.out.print("1110111");
                    }
                    else if(compString.equals("D-1")) //D-1
                    {
                        System.out.print("0001110");
                    }
                    else if(compString.equals("A-1")) //A-1
                    {
                        System.out.print("0110010");
                    }
                    else if(compString.equals("M-1")) //M-1
                    {
                        System.out.print("1110010");
                    }
                    else if(compString.equals("D+A") || compString.equals("A+D")) //D+A
                    {
                        System.out.print("0000010");
                    }
                    else if(compString.equals("D+M") || compString.equals("M+D")) //D+M
                    {
                        System.out.print("1000010");
                    }
                    else if(compString.equals("D-A")) //D-A
                    {
                        System.out.print("0010011");
                    }
                    else if(compString.equals("D-M")) //D-M
                    {
                        System.out.print("1010011");
                    }
                    else if(compString.equals("A-D")) //A-D
                    {
                        System.out.print("0000111");
                    }
                    else if(compString.equals("M-D")) //M-D
                    {
                        System.out.print("1000111");
                    }
                    else if(compString.equals("D&A") || compString.equals("A&D")) //D&A
                    {
                        System.out.print("0000000");
                    }
                    else if(compString.equals("D&M") ||  compString.equals("M&D")) //D&A
                    {
                        System.out.print("1000000");
                    }
                    else if(compString.equals("D|A") ||  compString.equals("A|D")) //D|A
                    {
                        System.out.print("0010101");
                    }
                    else if(compString.equals("D|M") ||  compString.equals("M|D")) //D|M
                    {
                        System.out.print("1010101");
                    }
                    else //we have a null error
                    {
                        System.out.print("Non-recongized null in the comp section of the c instruction");
                    }

                    //Now look at the different destination bits
                    String destString = parser.getDest();
                    if(destString != null){
                        if(destString.equals("M")){
                            System.out.print("001");
                        }
                        else if(destString.equals("D")){
                            System.out.print("010");
                        }
                        else if(destString.equals("DM")){
                            System.out.print("011");
                        }
                        else if(destString.equals("A")){
                            System.out.print("100");
                        }
                        else if(destString.equals("AM")){
                            System.out.print("101");
                        }
                        else if(destString.equals("AD")){
                            System.out.print("110");
                        }
                        else if(destString.equals("ADM")){
                            System.out.print("111");
                        }
                        else{
                            System.out.print("We have an null issue in the destination part of a c instruction");
                        }
                    }
                    else{ //null destination
                        System.out.print("000");
                    }

                    //Now finally look at the different jump combinations
                    String jumpString = parser.getjump();
                    if(jumpString != null){

                        //Now work through all the combinations of Jumping
                        if(jumpString.equals("JGT")){
                            System.out.print("001");
                        }
                        else if(jumpString.equals("JEQ")){
                            System.out.print("010");
                        }
                        else if(jumpString.equals("JGE")){
                            System.out.print("011");
                        }
                        else if(jumpString.equals("JLT")){
                            System.out.print("100");
                        }
                        else if(jumpString.equals("JNE")){
                            System.out.print("101");
                        }
                        else if(jumpString.equals("JLE")){
                            System.out.print("110");
                        }
                        else if(jumpString.equals("JMP")){
                            System.out.print("111");
                        }
                        else{
                            System.out.print("We have a null issue in the jump destination of C-instruction");
                        }
                    }
                    else{ //null jump
                        System.out.print("000");
                    }

                    //newline print
                    System.out.println();

                }
                else //we don't have a comp and now print out null valye
                    System.out.println("0000000000000");
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