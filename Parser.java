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
    }

    //Create the methods
    public boolean hasMoreLines(){return file.hasNext();} //has more lines

    public void advance(){ //advance to the next instuction
        boolean instructionSet = false;
        while(!instructionSet){ //loop file until we have instruction
            currentInstruction = file.next();

            //Check for white space and comments
            if(!currentInstruction.contains("//")){ //we a non-comment instruction

                instructionSet = true; //breaks the loop

                //Check for what instructions we have
                if(currentInstruction.charAt(0) == '@'){ //a
                    currentSymbol = currentInstruction;
                    instructionType = 'a';=
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
                }
            }
        }
    }

    public char getInstructionType(){return instructionType;}
    public String getSymbol(){return currentSymbol;}

    //Now build all the methods to return the destination, comp, and jump
    public String getDest(){
        
        if(instructionType == 'c'){ //only do calculations if we have a c-instruction
            if(currentInstruction.contains("=")){ //only if we have an equals will we have a destination
                return currentInstruction.substring(0, currentInstruction.indexOf("=")); //return everything before the equals sign
            }
        }
        return null;
    }
    public String getComp(){
        if(instructionType == 'c'){
            if(currentInstruction.contains("=")){ //only operate if there is an equal sign
                return currentInstruction.substring(currentInstruction.indexOf("=")+1, currentInstruction.indexOf(";"));
            }
        }
        return null;
    }
    public String getjump(){
        if(instructionType == 'c'){
            if(!currentInstruction.contains("=")){ //only have a jump when we don't have an equal sign
                return currentInstruction.substring(currentInstruction.indexOf(";"));
            }
        }
        return null;
    }
}
