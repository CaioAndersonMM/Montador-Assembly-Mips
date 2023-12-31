package fileTools.instructions;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exceptions.InvalidInstructionException;
import java.io.IOException;

public class Translator {
    private Map<String, Integer> labels;
    final int initialAddress = 4194304;

    public Translator(Map<String, Integer> labels) {
        this.labels = labels;
    }

    /**
     * @param instruction
     * @return
     * @throws IOException
     * @throws InvalidInstructionException
     */
    public String translateInstruction(String instruction, int lineCounter)
            throws IOException, InvalidInstructionException {
        String pattern = "\\w+\\d?:\\s*";
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(instruction);
        String result = matcher.replaceAll("");
        instruction = result.trim();

        // inicializa o objeto com as intruções
        Instructions instructions = new Instructions();
        Map<String, Map<String, String>> instructionsR = instructions.getInstructionsR();
        Map<String, Map<String, String>> instructionsI = instructions.getInstructionsI();
        Map<String, Map<String, String>> instructionsJ = instructions.getInstructionsJ();
        Map<String, String> command;
        String[] words = instruction.split("[, ()]+", 5);

        // se a instrução ficar vazia saia da função
        if (words[0].isEmpty()) {
            return "";
        }

        if ((command = instructionsR.get(words[0])) != null) {
            return this.translateR(words, command);
        }

        if ((command = instructionsI.get(words[0])) != null) {
            return this.translateI(words, command, lineCounter);
        }

        if ((command = instructionsJ.get(words[0])) != null) {
            return this.translateJ(words, command, lineCounter);
        }

        throw new InvalidInstructionException("Instrução: " + words[0] + " não encontrada");
    }

    private String translateR(String[] words, Map<String, String> command) throws InvalidInstructionException {
        BinaryConversor binaryConversor = new BinaryConversor();
        String opcode, rs, rt, rd, sa, function;

        opcode = command.get("opcode");
        function = command.get("function");
        rs = command.get("rs").contains("rs") ? "00000" : words[Integer.valueOf(command.get("rs"))];
        rt = command.get("rt").contains("rt") ? "00000" : words[Integer.valueOf(command.get("rt"))];
        rd = command.get("rd").contains("rd") ? "00000" : words[Integer.valueOf(command.get("rd"))];
        sa = command.get("sa").contains("sa") ? "00000" : words[Integer.valueOf(command.get("sa"))];

        // converte os registradores para seus binarios correspondentes
        rs = binaryConversor.registerToBinary(rs);
        rt = binaryConversor.registerToBinary(rt);
        rd = binaryConversor.registerToBinary(rd);
        sa = binaryConversor.registerToBinary(sa);

        return opcode + rs + rt + rd + sa + function;
    }

    private String translateI(String[] words, Map<String, String> command, int lineCounter)
            throws InvalidInstructionException {
        BinaryConversor binaryConversor = new BinaryConversor();
        String opcode, rs, rt, address;
        String immediate;
        opcode = command.get("opcode");
        rs = command.get("rs").contains("rs") ? "00000" : words[Integer.valueOf(command.get("rs"))];
        rt = command.get("rt").contains("rt") ? "00000" : words[Integer.valueOf(command.get("rt"))];
        address = command.get("const").contains("const") ? "0" : words[Integer.valueOf(command.get("const"))];

        if (labels.containsKey(address)) {
            address = Integer.toString(
                    (this.labels.get(words[Integer.valueOf(command.get("const"))]) -  lineCounter));
        }

        // conversão para binario
        rs = binaryConversor.registerToBinary(rs);
        rt = binaryConversor.registerToBinary(rt);
        immediate = binaryConversor.decimalToBinary(Integer.valueOf(address), 16);

        return opcode + rs + rt + immediate;
    }

    private String translateJ(String[] words, Map<String, String> command, int lineCounter)
            throws InvalidInstructionException {
        BinaryConversor binaryConversor = new BinaryConversor(); // Não precisa
        String opcode, address, pc;

        opcode = command.get("opcode");
        address = words[Integer.valueOf(command.get("address"))];

        if (labels.containsKey(address)) {
            address = Integer.toString((this.labels.get(address)));
        }

        address = binaryConversor.decimalToBinary(Integer.valueOf(initialAddress + 4 * Integer.valueOf(address)) / 4, 26);
        return opcode + address;
    }
}
