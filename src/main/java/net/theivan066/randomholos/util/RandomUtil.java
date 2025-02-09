package net.theivan066.randomholos.util;

import java.util.Random;

public class RandomUtil {
    public static final Random random = new Random();

    public static int nextInt(int origin, int bound) {
        if (origin == bound) {
            return origin;
        }
        if (origin > bound) {
            int i = origin;
            origin = bound;
            bound = i;
        }
        return random.nextInt(origin, bound);
    }

    public static float nextFloat(float origin, float bound) {
        if (origin == bound) {
            return origin;
        }
        if (origin > bound) {
            float f = origin;
            origin = bound;
            bound = f;
        }
        return random.nextFloat(origin, bound);
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }
}
