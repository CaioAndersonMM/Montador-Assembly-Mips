package fileTools.instructions;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Instructions {
    private Map<String, Map<String, String>> instructionsR;
    private Map<String, Map<String, String>> instructionsI;
    private Map<String, Map<String, String>> instructionsJ;
    private Map<String, String> subInstructions;

    public Instructions() throws IOException {
        this.setInstructuionsR();
        this.setInstructuionsI();
        this.setInstructuionsJ();
    }

    public Map<String, Map<String, String>> getInstructionsR() {
        return this.instructionsR;
    }

    public Map<String, Map<String, String>> getInstructionsI() {
        return this.instructionsI;
    }

    public Map<String, Map<String, String>> getInstructionsJ() {
        return this.instructionsJ;
    }

    public void setInstructuionsR() throws IOException {
        instructionsR = new HashMap<String, Map<String, String>>();

        try {
            String line;
            String[] words;
            FileReader fr = new FileReader("fileTools/instructions/instructionsDatabase/tipeR.txt");
            BufferedReader bf = new BufferedReader(fr);

            while ((line = bf.readLine()) != null) {
                subInstructions = new HashMap<String, String>();
                words = line.split(";", 10);
                subInstructions.put("opcode", words[1]);
                subInstructions.put("function", words[2]);
                subInstructions.put("rs", words[3]);
                subInstructions.put("rt", words[4]);
                subInstructions.put("rd", words[5]);
                subInstructions.put("sa", words[6]);
                this.instructionsR.put(words[0], subInstructions);
            }

            bf.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInstructuionsI() throws IOException {
        instructionsI = new HashMap<String, Map<String, String>>();

        try {
            String line;
            String[] words;
            FileReader fr = new FileReader("fileTools/instructions/instructionsDatabase/tipeI.txt");
            BufferedReader bf = new BufferedReader(fr);
            while ((line = bf.readLine()) != null) {
                subInstructions = new HashMap<String, String>();
                words = line.split(";", 6);
                subInstructions.put("opcode", words[1]);
                subInstructions.put("rs", words[2]);
                subInstructions.put("rt", words[3]);
                subInstructions.put("const", words[4]);
                this.instructionsI.put(words[0], subInstructions);
            }

            bf.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInstructuionsJ() throws IOException {
        instructionsJ = new HashMap<String, Map<String, String>>();

        try {
            String line;
            String[] words;
            FileReader fr = new FileReader("fileTools/instructions/instructionsDatabase/tipeJ.txt");
            BufferedReader bf = new BufferedReader(fr);
            while ((line = bf.readLine()) != null) {
                subInstructions = new HashMap<String, String>();
                words = line.split(";", 3);
                subInstructions.put("opcode", words[1]);
                subInstructions.put("address", words[2]);
                this.instructionsJ.put(words[0], subInstructions);
            }

            bf.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

}
