
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import fileOperations.CopyFileToProject;
import fileTools.FileAssembly;
import fileTools.instructions.*;

public class Assembly {
    public static void main(String[] args) {
        // Copia arquivo para o projeto
        CopyFileToProject copyFile = new CopyFileToProject();
        copyFile.main(args);

        try {
            String line;
            // FileAssembly f = new FileAssembly("arquivo.asm");
            FileAssembly f = new FileAssembly("files/instrucoes.asm");
            Map<String, Integer> labels = f.countLinesWithLabels();
            f.fileRead();
            BufferedReader bf = new BufferedReader(f.getFile());
            Translator tr = new Translator(labels);

            int i = 1;

            ArrayList<String> instructions = new ArrayList<>(); // pegar instruções do arquivos

            while ((line = bf.readLine()) != null) {
                // Se linha não for vazia e não tiver nada após o label
                if (!line.isEmpty() && !line.substring(line.lastIndexOf(":") + 1).trim().isEmpty()) {
                    System.out.println(tr.translateInstruction(line, i));
                    instructions.add(tr.translateInstruction(line, i));
                }
                i++;
            }

            i = 1;
            String fileName = "output.hex";
            BinaryConversor binaryConversor = new BinaryConversor();

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                writer.println("v2.0 raw");
                for (String instruction : instructions) {
                    writer.print(binaryConversor.binaryToHexa(instruction) + " ");
                    if (i >= 4) {
                        writer.print("\n");
                        i = 0;
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}