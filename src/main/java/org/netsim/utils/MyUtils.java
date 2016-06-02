package org.netsim.utils;

import java.util.ArrayList;

public class MyUtils {

    /* This method returns the Short[] array of a primitive short[] array*/
    public static Short[] shortToShort (short a[] ) {
        Short result[] = new Short[a.length];
        for (int i=0; i < result.length; i++)
            result[i] = new Short(a[i]);

        return result;
    }

    /* This method returns the primitive short[] array of a Short[] array*/
    public static short[] shortToShort (Short a[]) {
        short result[] = new short[a.length];
        for (int i=0; i < result.length; i++)
            result[i] = a[i].shortValue();

        return result;
    }

    /* This method returns the OR operation of two arrays of bits (type short)
     * Both parameters must have the same length */
    public static short[] OR(short a[], short b[]) {
        if (a.length == b.length) {
            short result[] = new short[a.length];
            for (int i=0; i < a.length; i++)
                result[i] = (short)(a[i] | b[i]);

            return result;
        } else {
            return null;
        }
    }

    /* This method returns the OR operation of two arrays of bits (type Short)
     * Both parameters must have the same length */
    public static Short[] OR(Short a[], Short b[]) {
        if (a.length == b.length) {
            Short result[] = new Short[a.length];
            for (int i=0; i < a.length; i++) 
                result[i] = new Short((short)(a[i] | b[i]));

            return result;
        } else {
            return null;
        }
    }

    /* This method returns the AND operation of two arrays of bits
     * Both parameters must have the same length */
    public static short[] AND(short a[], short b[]) {
        if (a.length == b.length) {
            short result [] = new short[a.length];
            for (int i=0; i < a.length; i++)
                result[i] = (short)(a[i] & b[i]);

            return result;
        } else {
            return null;
        }
    }
    
    /* This method returns true if a and b (arrays of bits) are equal */
    public static boolean EQUAL(short a[], short b[]) {
        if (a.length == b.length) {
            boolean equal = true;
            for (int i=0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    equal = false;
                    break;
                }
            }

            return equal;
        } else {
            return false;
        }
    }

    /* This method returns a permuted array of bits according to a 
     * permutation pattern (type short).
     * Both Parameters must have the same length */
    public static short[] permute(short a[], int perm[]) {
        if (a.length == perm.length) {
            short result[] = new short[a.length];
            for (int i=0; i < a.length; i++)
                result[i] = (short)(a[perm[i]]);

            return result;
        } else {
            return null;
        }
    }

    /* This method returns the invers permuted array of bits according to a 
     * permutation pattern (type short).
     * Both parameters must have the same length */
    public static short[] permuteInv(short a[], int perm[]) {
        if (a.length == perm.length) {
            short result[] = new short[a.length];
            for (int i=0; i < a.length; i++)
                result[perm[i]] = (short)(a[i]);

            return result;
        } else {
            return null;
        }
    }

    /* This method returns the inverse permuted array of bits according to a
     * permutation pattern (type Short).
     * Both parameters must have the same length */
    public static Short[] permuteInv(Short a[], int perm[]) {
        if (a.length == perm.length) {
            Short result[] = new Short[a.length];
            for (int i=0; i< a.length; i++)
                result[perm[i]] = new Short((short)(a[i]));

            return result;
        } else {
            return null;
        }
    }

    public static void displayBits(short[] arrayOfBits) {
        for(int i=0; i < arrayOfBits.length; i++) {
            System.out.print(arrayOfBits[i] + "");
        }
        System.out.println();
    }

    public static void displayArrayOfBits(ArrayList<Short[]> list) {
        for (int i=0; i < list.size(); i++) {
            displayBits(shortToShort(list.get(i)));
        }
        System.out.println();
    }

    public static void displayNumbers(int[] arrayOfNumbers) {
        for (int i=0; i < arrayOfNumbers.length; i++)
            System.out.print(arrayOfNumbers[i] + " ");

        System.out.println();
    }
}
