package org.netsim.networking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class MacAddressGeneratorTest {

    private MacAddressGenerator generator;

    @Before
    public void initialize() {
        generator = MacAddressGenerator.getInstance();
    }

    @Test
    public void shouldCreateValidMacAddress() {
        int maxPairValue = 255;
        int expectedNumMacPairs = 6;
        String message;
        String actualMac;
        String[] actualMacPairs;
        int actualNumMacPairs;
        int pairValue;

        int count = 0;
        while (count < 512) {
            actualMac = generator.newMacAddress();
            actualMacPairs = actualMac.split(":");
            actualNumMacPairs = actualMacPairs.length;

            for (String pair: actualMacPairs) {
                message = "Each byte should follow: 0 <= byte <= 255";
                pairValue = Integer.valueOf(pair, 16);
                assertTrue(message, 0 <= pairValue && pairValue <= 255);
            }
            message = "Mac addressess should have exactly 6 pairs of bytes...";
            assertEquals(message, expectedNumMacPairs, actualNumMacPairs);

            count += 1;
        }
    }
}
