package fileTools.instructions;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Instructions
{
    private Map <String, Map<String, String>> instructions;
    private Map <String, String> subInstructions;

    public Instructions() throws IOException
    {
        this.setInstructuionsR();
    }


    public Map <String, Map<String, String>> getInstructionsR()
    {
        return this.instructions;
    }

    public void setInstructuionsR() throws IOException
    {
        instructions = new HashMap<String, Map<String, String>>();

        try {
            String line;
            String[] words;
            FileReader fr = new FileReader("fileTools/instructions/tipeR.txt");
            BufferedReader bf = new BufferedReader(fr);

            while((line = bf.readLine()) != null)
            {
                subInstructions = new HashMap<String, String>();
                words = line.split(";", 3);
                subInstructions.put("opcode", words[1]);
                subInstructions.put("function", words[2]);
                instructions.put(words[0], subInstructions);
            }
        } catch (FileNotFoundException e) {
           System.err.println(e.getMessage());
        }
    }
    
}
