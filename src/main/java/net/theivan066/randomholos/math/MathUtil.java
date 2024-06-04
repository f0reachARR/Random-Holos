package net.theivan066.randomholos.math;

public class MathUtil {
    public static double square (double d) {
        return d * d;
    }
    public static double sqrDistance (double d1, double d2) {
        return square(d1) + square(d2);
    }
    public static double sqrDistance (double d1, double d2, double d3) {
        return square(d1) + square(d2) + square(d3);
    }
}
