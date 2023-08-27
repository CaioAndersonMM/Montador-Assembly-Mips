package fileTools.instructions;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TipeR {
    static public String translateInstruction(String instruction) throws IOException {
        String rs, rt, rd, sa;
        String[] words = instruction.split("[, $]+", 5); // elimina o cifrão tmb

        Instructions Tr = new Instructions();

        if (words.length > 3) {
            if (words[0].equals("sll") || words[0].equals("srl")) {
                System.out.println("comando sll ou srl");
                rs = stringToBinary("0"); // rs é zerado
                rt = stringToBinary(words[2]); // rt recebe o segundo registrador
                sa = stringToBinary(words[3]); // sa recebe o terceiro registrador
            } else {
                rs = stringToBinary(words[2]); // rs recebe o segundo registrador
                rt = stringToBinary(words[3]); // rt recebe o terceiro registrador
                sa = stringToBinary("0");
            }

            rd = stringToBinary(words[1]);

            // System.out.println(words[0]);

        } else if (words.length > 2) {
            System.out.println("ain");
            rs = stringToBinary(words[1]); // rs recebe o primeiro registrador
            rt = stringToBinary(words[2]); // rt recebe o segundo registrador
            rd = stringToBinary("0");
            sa = stringToBinary("0");
        } else {
            rs = stringToBinary(words[1]); // rs recebe o primeiro registrador
            rt = stringToBinary("0");
            rd = stringToBinary("0");
            sa = stringToBinary("0");
        }

        System.out.println(Tr.getInstructionsR().get(words[0]).get("opcode") + rs + rt + rd
                + sa + Tr.getInstructionsR().get(words[0]).get("function"));

        return "";
    }

    public static String stringToBinary(String input) {
        int intValue = Integer.parseInt(input);

        // Certifica-se de que o valor está dentro do intervalo de 0 a 31 (5 bits)
        if (intValue < 0 || intValue > 31) {
            throw new IllegalArgumentException("O valor deve estar entre 0 e 31 para caber em 5 bits.");
        }

        // Converte o valor numérico em sua representação binária de 5 bits
        String binaryValue = Integer.toBinaryString(intValue);

        // Preenche com zeros à esquerda para garantir 5 bits
        while (binaryValue.length() < 5) {
            binaryValue = "0" + binaryValue;
        }

        return binaryValue;
    }
}
