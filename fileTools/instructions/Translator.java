package fileTools.instructions;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.InvalidInstructionException;
import java.io.IOException;

public class Translator 
{
    final private String[] mipsRegistersNames = {
        "$zero", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3",
        "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7",
        "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7",
        "$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"
    };

    final private String[] mipsRegistersNumbers = {
        "$0", "$1", "$2", "$3", "$4", "$5", "$6", "$7",
        "$8", "$9", "$10", "$11", "$12", "$13", "$14", "$15",
        "$16", "$17", "$18", "$19", "$20", "$21", "$22", "$23",
        "$24", "$25", "$26", "$27", "$28", "$29", "$30", "$31"
    };


    /**
     * @param instruction
     * @return
     * @throws IOException
     * @throws InvalidInstructionException
     */
    static public String translateInstruction(String instruction) throws IOException, InvalidInstructionException
    {   
        String pattern = "\\w+\\d?:\\s*";

        Pattern regexPattern = Pattern.compile(pattern);

        Matcher matcher = regexPattern.matcher(instruction);

        String result = matcher.replaceAll("");

        instruction = result.trim();

        // inicializa o objeto com as intruções
        Instructions instructions = new Instructions();
        Map <String, Map<String, String>> instructionsR = instructions.getInstructionsR();
        Map<String, String> command;
        String opcode, rs, rt, rd, sa, function;
        String[] words = instruction.split("[, ]+", 5);
        Translator binaryTranslator = new Translator();
        
        // se a instrução ficar vazia saia da função
        if(words[0].isEmpty())
        {
            return "";
        }

        if((command = instructionsR.get(words[0])) != null)
        {
            opcode = command.get("opcode");
            function = command.get("function");
            rs = command.get("rs").contains("rs") ? "00000" : words[Integer.valueOf(command.get("rs"))];
            rt = command.get("rt").contains("rt") ? "00000" : words[Integer.valueOf(command.get("rt"))];
            rd = command.get("rd").contains("rd") ? "00000" : words[Integer.valueOf(command.get("rd"))];
            sa = command.get("sa").contains("sa") ? "00000" : words[Integer.valueOf(command.get("sa"))];

            // converte os registradores para seus binarios correspondentes
            rs = binaryTranslator.registerToBinary(rs);
            rt = binaryTranslator.registerToBinary(rt);
            rd = binaryTranslator.registerToBinary(rd);
            sa = binaryTranslator.registerToBinary(sa);
            
            return opcode + rs + rt + rd + sa + function;
        }

       throw new InvalidInstructionException("Instrução: " + words[0] + " Não encontrada");
    }

    /**
     * @param register
     * @return binary value of register
     * @throws InvalidInstructionException
     */
    private String registerToBinary(String register) throws InvalidInstructionException
    {
        int indexRegister = -1;

        if(register == "00000") {
            return register;
        }

        if(isInteger(register)) {
            return decimalToBinary(Integer.parseInt(register));
        }

        for(int i = 0; i < 32 & indexRegister == -1; i++)
        {
            if(this.mipsRegistersNames[i].equals(register))
            {
                indexRegister = i;
            }

            if(this.mipsRegistersNumbers[i].equals(register))
            {
                indexRegister = i;
            }
        }

        if(indexRegister == -1)
        {
            throw new InvalidInstructionException(register + " É invalido");
        }

        return decimalToBinary(indexRegister);
    } 

    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

    private static String decimalToBinary(int number) {
        String binaryRepresentation = Integer.toBinaryString(number);

        while(binaryRepresentation.length() < 5) {
            binaryRepresentation = "0" + binaryRepresentation;
        }

        return binaryRepresentation;
    }
}


