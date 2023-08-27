package fileTools.instructions;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TipeR 
{
    static public String translateInstruction(String instruction) throws IOException
    {
        String opcode, rs, rt, rd, sa, function;
        String[] words = instruction.split("[, ]+", 5);
        if(words.length > 3)
        {
            Instructions Tr = new Instructions();
            System.out.println(Tr.getInstructionsR());
        }

       return ""; 
    }
}


