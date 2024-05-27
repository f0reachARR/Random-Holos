package net.theivan066.randomholos.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum AzkiVariant {
    DEFAULT(0),
    SECOND(1),
    FOURTH(2),
    DRESS(3);

    private static final AzkiVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(AzkiVariant::getId)).toArray(AzkiVariant[]::new);
    private final int id;

    AzkiVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static AzkiVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
