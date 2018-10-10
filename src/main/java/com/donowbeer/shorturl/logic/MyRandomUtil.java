package com.donowbeer.shorturl.logic;

import java.util.Random;

public class MyRandomUtil {

    private static Random random = new Random();

    public static int generateRandomInt(int max) {
        return random.nextInt(max);
    }

}
