import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assembly {
    public static void main(String[] args) {
        String arquivo = "arquivo.asm";
        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            int contadorlinha = 0;
            int contadorendereco = 0;

            int capacidadeInicial = 10;
            long[] enderecos = new long[capacidadeInicial];

            // Primeiro ler procurando rótulos:
            contadorlinha = 0; // Reiniciar o contador de linhas
            fileReader = new FileReader(arquivo); // Reabrir o arquivo
            bufferedReader = new BufferedReader(fileReader); // Reabrir o bufferedReader

            while ((linha = bufferedReader.readLine()) != null) {
                if ((linha.contains(":"))) { // se a linha lida possui ':' indicador de rotulo
                    enderecos[contadorendereco] = contadorlinha * 4 + (400000); // 0x00400000 inicial
                    contadorendereco++;
                    contadorlinha++;
                    System.out.println(linha);
                } else {
                    contadorlinha++;
                }
            }

            // Segunda leitura
            while ((linha = bufferedReader.readLine()) != null) {

            }

            System.out.println(enderecos[0]);
            System.out.println(enderecos[1]);
            System.out.println(enderecos[2]);

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}