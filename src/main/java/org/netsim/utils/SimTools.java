package org.netsim.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimTools {

    /* This method returns the max value of an array */
    public static int max(int[] values) {
        int max = (int)Double.MIN_VALUE;
        for (int i=0; i < values.length; i++) {
            if (values[i] > max)
                max = values[i];
        }
        
        return max;
    }

    /* Procedure to generate the "almost unique" link ID randomly
     *      m: number of bits of the ID
     *      k: number of 1's (they have to be a very few)
     * There are m!/[k!(m-k)]! different combinations
     * For example: m=256, k=5, gives 8,809'549,056 possible link IDs */
    public static short[] generateRandomID(int m, int k) {
        short randomID[] = new short[m];
        double prob0 = ((double)(m-k))/m;
        Random randomGenerator = new Random();
        double r;
        int sum1s = 0;

        while (sum1s != k) {
            sum1s = 0;
            for (int i=0; i < m; i++) {
                r = randomGenerator.nextDouble();
                if (r <= prob0)
                    randomID[i] = 0;
                else {
                    randomID[i] = 1;
                    sum1s++;
                }
            }
        }

        return randomID;
    }

    /* Procedure to generate the bit pattern
     *      m: size */
    public static int[] generateBitPermPattern(int m) {
        ArrayList<Integer> numbers = new ArrayList <Integer>();
        
        for (int i=0; i < m; i++)
            numbers.add(i);

        Collections.shuffle(numbers);

        int permNumbers[] = new int[m];
        for (int i=0; i < m; i++)
            permNumbers[i] = numbers.get(i).intValue();

        return permNumbers;
    }

    public static int distToSource(String shiiiet) {
        // TODO: refactoring, decoupling
        return 1;
    }
}
