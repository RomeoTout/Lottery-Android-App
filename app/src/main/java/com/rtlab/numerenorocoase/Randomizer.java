package com.rtlab.numerenorocoase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Randomizer {


    //returns a random number to select from the name list
    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    //generates multiple numbers without duplicating
    static String randomNoCopy(int max, int count){
        List<Integer> numberArray = new ArrayList<>();
        StringBuilder result = new StringBuilder("  ");
        int min = 1;
        for(int i = min; i <= max; i++){
            numberArray.add(i);
        }
        Collections.shuffle(numberArray);

        for(int i = 0; i < count; i++){
            result.append(numberArray.get(i)).append("  ");
        }
        return result.toString();
    }
}
