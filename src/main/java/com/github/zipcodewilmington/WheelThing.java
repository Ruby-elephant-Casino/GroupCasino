package com.github.zipcodewilmington;

import java.util.Random;

public class WheelThing {
    private Random random;

    public void Wheel() {
        random = new Random();
    }

    public int spin(int min, int max) {
        int result = random.nextInt((max - min) + 1) + min;
        return result;
    }
}
