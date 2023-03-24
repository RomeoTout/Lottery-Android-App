package com.rtlab.numerenorocoase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRandomWithRange(){
        int min = 1;
        int max = 10;
        int result = Randomizer.randomWithRange(min, max);
        assertTrue(result >= min && result <= max);
    }

    @Test
    public void testRandomNoCopy() {
        int max = 10;
        int count = 5;
        String result = Randomizer.randomNoCopy(max, count);
        System.out.println("result: '" + result + "'");

        // check that the result string contains the correct number of integers
        String[] parts = result.trim().split("\\s+");
        System.out.println("parts: " + Arrays.toString(parts));
        assertEquals(count, parts.length);

        // check that each integer in the result string is within the specified range
        Set<Integer> integers = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int integer = Integer.parseInt(parts[i]);
            assertTrue(integer >= 1 && integer <= 10);
            assertFalse(integers.contains(integer));
            integers.add(integer);
        }
    }

}

