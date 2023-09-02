package fileOperations;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChoose {
    public static void main(String[] args) {
        // Crie uma instância do seletor de arquivos
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos ASM", "asm");
        fileChooser.setFileFilter(filter);

        // Crie uma janela de diálogo para o seletor de arquivos
        JFrame frame = new JFrame("Selecione um Arquivo (.asm)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Exiba o seletor de arquivos e capture a escolha do usuário
        int returnValue = fileChooser.showOpenDialog(frame);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // O usuário selecionou um arquivo
            java.io.File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Arquivo selecionado: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }

        frame.pack();
        frame.setVisible(true);
    }
}
