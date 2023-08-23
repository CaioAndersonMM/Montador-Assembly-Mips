import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Assembly {
    public static void main(String[] args) {
        String arquivo = "arquivo.asm";

        // $t0 a $t7: 8 a 15
        // $s0 a $s7: 16 a 23

        // Mapeamento de nomes de registradores para números
        Map<String, String> nomeParaNumero = new HashMap<>();
        nomeParaNumero.put("$s0", "16");
        nomeParaNumero.put("$s1", "17");
        nomeParaNumero.put("$s2", "18");
        nomeParaNumero.put("$s3", "19");
        nomeParaNumero.put("$s4", "20");
        nomeParaNumero.put("$s5", "21");
        nomeParaNumero.put("$s6", "22");
        nomeParaNumero.put("$s7", "23");
        // mapeado

        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            int contadorlinha = 0;
            int contadorendereco = 0;

            int capacidadeInicial = 10;
            long[] enderecos = new long[capacidadeInicial];

            // Primeiro ler procurando rótulos:

            while ((linha = bufferedReader.readLine()) != null) {
                if ((linha.contains(":"))) { // se a linha lida possui ':' indicador de rotulo
                    enderecos[contadorendereco] = contadorlinha * 4 + (400000); // 0x00400000 inicial
                    contadorendereco++;
                    contadorlinha++;
                } else {
                    contadorlinha++;
                }
            }

            contadorlinha = 0; // Reiniciar o contador de linhas
            fileReader = new FileReader(arquivo); // Reabrir o arquivo
            bufferedReader = new BufferedReader(fileReader); // Reabrir o bufferedReader

            String instrucao;
            // Segunda leitura
            while ((linha = bufferedReader.readLine()) != null) {
                if (linha.contains("sll")) {
                    System.out.println("entrou em sll");
                    boolean ehInteiro = true;
                    String rd = "";
                    String rs = "";
                    String rt = "";

                    int indiceDolar = linha.indexOf("$");
                    if (indiceDolar + 1 < linha.length() && Character.isDigit(linha.charAt(indiceDolar + 1))) {
                        rd += linha.charAt(indiceDolar + 1);

                        // Verificar se há um segundo número após o primeiro
                        if (indiceDolar + 2 < linha.length() && Character.isDigit(linha.charAt(indiceDolar + 2))) {
                            rd += linha.charAt(indiceDolar + 2);
                        }

                        // Para rs

                        // Continuar a lógica para verificar outros números na linha
                        int proximoEspaco = linha.indexOf("$", indiceDolar + rs.length() + 1);
                        if (proximoEspaco + 1 < linha.length() && Character.isDigit(linha.charAt(proximoEspaco + 1))) {
                            rs += linha.charAt(proximoEspaco + 1);
                            System.out.println("Próximo número: " + rs);

                            // Verificar se há um segundo número após o primeiro
                            if (indiceDolar + 2 < linha.length() && Character.isDigit(linha.charAt(indiceDolar + 2))) {
                                rd += linha.charAt(indiceDolar + 2);
                            }
                            // Continuar a lógica para lidar com o próximo número da instrução
                        }

                        // Para rt
                        int proximoEspaco2 = linha.indexOf(",", proximoEspaco + rt.length());
                        if (proximoEspaco2 != -1) {
                            // Encontramos a vírgula, então agora podemos verificar o número após ela
                            int indiceNumero = proximoEspaco2 + 1;
                            while (indiceNumero < linha.length()
                                    && Character.isWhitespace(linha.charAt(indiceNumero))) {
                                // Pular espaços em branco
                                indiceNumero++;
                            }
                            if (indiceNumero < linha.length() && Character.isDigit(linha.charAt(indiceNumero))) {
                                // Encontramos um número após a vírgula
                                rt += linha.charAt(indiceNumero);

                                // Verificar se há um segundo número após o primeiro para rt
                                if (indiceNumero + 1 < linha.length()
                                        && Character.isDigit(linha.charAt(indiceNumero + 1))) {
                                    rt += linha.charAt(indiceNumero + 1);
                                }
                            }
                        }

                        System.out.println("0" + rd + rs + rt);
                        // instrução = "0" + rs + rt + rd + sa + "0";
                    } else {
                        ehInteiro = false; // Se não for dígito, não é um inteiro!
                    }

                    if (ehInteiro) {
                        System.out.println("0" + rd);
                        // instrução = "0" + rs + rt + rd + sa + "0";
                    } else {
                        System.out.println("Não é um número inteiro válido");
                    }
                } else if (linha.contains("beq")) {
                    System.out.println("entrou em beq");
                    // Lógica para "beq"
                } else if (linha.contains("jump")) {
                    System.out.println("entrou em jump");
                    // Lógica para "jump"
                } else {
                    System.out.println("Instrução não reconhecida");
                }

            }

            System.out.println(enderecos[0]);
            System.out.println(enderecos[1]);
            System.out.println(enderecos[2]);

            bufferedReader.close();
        } catch (

        IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}