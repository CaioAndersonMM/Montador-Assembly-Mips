
import java.io.BufferedReader;
import java.util.Map;
import fileTools.FileAssembly;
import fileTools.instructions.*;
import fileTools.instructions.BinaryConversor;

public class Assembly 
{
    public static void main(String []args) {
        try {
            String line;
            FileAssembly f = new FileAssembly("arquivo.asm");
            Map<String, Integer> labels = f.countLinesWithLabels();
            f.fileRead();
            BufferedReader bf = new BufferedReader(f.getFile());
            Translator tr = new Translator(labels);

            int i = 1;

            while((line = bf.readLine()) != null)
            {
                System.out.println(tr.translateInstruction(line, i));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


