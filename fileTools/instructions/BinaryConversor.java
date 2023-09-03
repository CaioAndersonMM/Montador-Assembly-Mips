package fileTools.instructions;

import java.math.BigInteger;

import exceptions.InvalidInstructionException;

public class BinaryConversor {
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
     * @param register
     * @return binary value of register
     * @throws InvalidInstructionException
     */
    public String registerToBinary(String register) throws InvalidInstructionException {
        int indexRegister = -1;

        if (register == "00000") {
            return register;
        }

        if (isInteger(register)) {
            return decimalToBinary(Integer.parseInt(register), 5);
        }

        for (int i = 0; i < 32 & indexRegister == -1; i++) {
            if (this.mipsRegistersNames[i].equals(register)) {
                indexRegister = i;
            }

            if (this.mipsRegistersNumbers[i].equals(register)) {
                indexRegister = i;
            }
        }

        if (indexRegister == -1) {
            throw new InvalidInstructionException(register + " É invalido");
        }

        return decimalToBinary(indexRegister, 5);
    }

    private static boolean isInteger(String str) {
        return str != null && str.matches("-?[0-9]*");
    }

    public String decimalToBinary(int number, int lenght) {
        String binaryRepresentation = Integer.toBinaryString(number);
        String singBit = binaryRepresentation.substring(1) == "1" ? "1" : "0";

        while (binaryRepresentation.length() < lenght) {
            binaryRepresentation = singBit + binaryRepresentation;
        }

        while (binaryRepresentation.length() > lenght) {
            binaryRepresentation = binaryRepresentation.substring(1);
        }

        return binaryRepresentation;
    }

    public String binaryToHexa(String number) {
        BigInteger decimal = new BigInteger(number, 2); // Para caber mais
        String hexaRepresentation = decimal.toString(16).toUpperCase();
        return hexaRepresentation;
    }
}
