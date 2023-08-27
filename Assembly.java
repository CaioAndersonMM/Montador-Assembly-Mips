import java.io.BufferedReader;
import fileTools.FileAssembly;
import fileTools.instructions.*;

public class Assembly 
{
    public static void main(String []args) {
        String line;
        try {
            FileAssembly f = new FileAssembly("arquivo.asm");
            f.fileRead();
            BufferedReader bf = new BufferedReader(f.getFile());
            while((line = bf.readLine()) != null)
            {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


