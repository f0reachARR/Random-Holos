package net.theivan066.randomholos.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum MikopVariant {
    DEFAULT(0),
    NYE(1),
    SHADES(2),
    SHADES_2(3);

    private static final MikopVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MikopVariant::getId)).toArray(MikopVariant[]::new);
    private final int id;

    MikopVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MikopVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
