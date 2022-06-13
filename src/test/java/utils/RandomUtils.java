package utils;

import java.util.Random;

public class RandomUtils {

    public static String getRandomFromInputArray(String[] inputArray) {
        Random random = new Random();
        return inputArray[random.nextInt(inputArray.length)];
    }
}
