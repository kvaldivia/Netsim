package org.netsim.core;

import java.util.Random;

public class MacAddressGenerator {
    private static MacAddressGenerator instance = new MacAddressGenerator();
    private String base;
    private int maxByteValue;
    private int[] nextComplement;

    private MacAddressGenerator(){
        base = "AA:AA:AA";
        maxByteValue = 255;
        nextComplementReset();
    }

    public static MacAddressGenerator getInstance() {
        return instance;
    }

    private void nextComplementReset() {
        nextComplement = new int[3];

        for (int n=0; n < nextComplement.length; n++) {
            nextComplement[n] = 0;
        }
    }

    private int[] getNextComplement() {
        int[] result = nextComplement;
        
        while (true) {
            nextComplement[2] += 1;
            if (nextComplement[2] > maxByteValue) {
                nextComplement[2] = 0;
                nextComplement[1] += 1;
            }
            if (nextComplement[1] > maxByteValue) {
                nextComplement[1] = 0;
                nextComplement[0] += 1;
            }
            if (nextComplement[0] > 255) {
                nextComplementReset();
            }
            break;
        }

        return result;
    }

    public String newMacAddress() {
        String result = "";
        int[] complement = getNextComplement();

        for (int n=0; n < complement.length; n++) {
            result += ":" + Integer.toHexString(complement[n]);
        }

        result = this.base + result;

        return result;
    }

    public static void main(String[] args) {
        MacAddressGenerator test = new MacAddressGenerator();
        String macAddress = test.newMacAddress();
        System.out.println(macAddress);
    }
}
