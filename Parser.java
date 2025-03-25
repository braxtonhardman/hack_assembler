//Parser module
//Braxton HArdman, Ethan Emerson, Christian Sapp
import java.io.*;
import java.util.*;

public class Parser 
{
    //instance variables
    private String fileName;
    private String currentSymbol;
    private String currentInstruction;
    private char instructionType;
    private Scanner file;

    //Create constructor
    public Parser(String fileName){

        //Read the file
        this.fileName = fileName;
        try{
            file = new Scanner(new File(this.fileName));
        }catch(FileNotFoundException fnfe){
            System.out.println("File NOT FOUND!");
        }
        currentLineNumber = 0;
    }

    //Create the methods
    public boolean hasMoreLines(){return file.hasNext();} //has more lines

    int currentLineNumber = 0;

    public void advance(){ //advance to the next instuction
        boolean instructionSet = false;
        while(!instructionSet && file.hasNext()){ //loop file until we have instruction
            currentInstruction = file.nextLine();
            currentInstruction = currentInstruction.trim();
            //Check for white space and comments
            try{
                if(!currentInstruction.contains("//")){ //we a non-comment instruction
                    
                    System.out.println(currentInstruction+" "+currentLineNumber);
                    instructionSet = true; //breaks the loop
                    //Check for what instructions we have
                    if(currentInstruction.charAt(0) == '@'){ //a
                        currentSymbol = currentInstruction.substring(1);
                        instructionType = 'a';
                        currentLineNumber++;

                    }
                    else if(currentInstruction.charAt(0) == '(') //label instruction
                    {
                        currentSymbol = currentInstruction.substring(1, currentInstruction.length() - 1); //cut off the parenthesis
                        instructionType = 'l';
                    }
                    else //we have a C-instruction
                    {
                        instructionType = 'c';
                        currentSymbol = null;
                        currentLineNumber++;
                    }
                }
            }
            catch(IndexOutOfBoundsException ioobe){ //this is to catch an index out of bounds error to help pass over whitespace
            }   
        }
    }

    public char getInstructionType(){return instructionType;}
    public String getSymbol(){return currentSymbol;}

    //Now build all the methods to return the destination, comp, and jump
    public String getDest(){
         if(instructionType == 'c'){ 
            if(currentInstruction.contains("=")){
               return currentInstruction.substring(0, currentInstruction.indexOf("="));
            }
         }
         return null;
    }
    
    public String getComp(){
    if(instructionType == 'c'){
        if(currentInstruction.contains("=")){
            if(currentInstruction.contains(";")){
                return currentInstruction.substring(currentInstruction.indexOf("=")+1, currentInstruction.indexOf(";"));
            } else {
                return currentInstruction.substring(currentInstruction.indexOf("=")+1);
            }
        } else if(currentInstruction.contains(";")){
            return currentInstruction.substring(0, currentInstruction.indexOf(";"));
        } else {
            return currentInstruction; // Just comp with no dest or jump
        }
    }
    return null;
    }
    
    public String getjump(){
        if(instructionType == 'c'){
            if(!currentInstruction.contains("=") && currentInstruction.contains(";")){ //only have a jump when we don't have an equal sign
                return currentInstruction.substring(currentInstruction.indexOf(";")+1);
            }
        }
        return null;
    }

    public int getLineNumber(){
        return currentLineNumber;
    }
}
