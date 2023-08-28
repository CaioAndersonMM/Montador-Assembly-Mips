package fileTools;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import exceptions.InvalidFileException;

public class FileAssembly
{
    private String fileName;
    private FileReader file;
    final int initialAddress = 4194304; // endereço de memoria inicial do assembly equivalente a 0x00400000

    public FileAssembly(String fileName) throws Exception
    {
        this.setFileName(fileName);
    }

    public void setFileName(String fileName) throws Exception
    {
        if(fileName == "") {
            throw new InvalidFileException("Nome de arquivo inválido");
        }
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public FileReader getFile()
    {
        return this.file;
    }

    public void fileRead() throws Exception
    {
        this.file = new FileReader(this.fileName);
    }

    // lê o arquivo e retorna todas as linhas que contem a string especificada
    // junto com o endereço de memoria relativo delas
    public Map<String, Integer> countLinesWithLabels() throws InvalidFileException, FileNotFoundException
    {
        String line, labelName;
        int lineCounter = 0;
        Map<String, Integer> adresses = new HashMap<String, Integer>();
        FileReader file = new FileReader(this.fileName);
        BufferedReader bf = new BufferedReader(file);

        try {
            while ((line = bf.readLine()) != null) {
                if ((line.contains(":"))) {
                    labelName = line.split(":")[0];
                    adresses.put(labelName, initialAddress + 4 * lineCounter);       
                }
                lineCounter++;
            }      
            if(lineCounter == 0) {
                bf.close();
                throw new InvalidFileException("Não foi possivel ler o arquivo");
            }   
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return adresses;
    }
}