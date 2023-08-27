import java.io.BufferedReader;
import fileTools.FileAssembly;
import fileTools.instructions.*;
import java.util.Map;

public class Assembly2 {
    public static void main(String[] args) {
        try {
            FileAssembly f = new FileAssembly("arquivo.asm");
            f.fileRead();

            TipeR.translateInstruction("jr $8");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
