package fileOperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Paths;

public class CopyFileToProject {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            Path destinationPath = Paths.get("files/instrucoes.asm");

            try {
                Files.createDirectories(destinationPath.getParent());
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Arquivo copiado com sucesso para o projeto.");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro ao copiar o arquivo.");
            }
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }
    }
}
