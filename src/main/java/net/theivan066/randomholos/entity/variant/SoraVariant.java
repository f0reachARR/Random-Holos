package net.theivan066.randomholos.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum SoraVariant {
    DEFAULT(0),
    CASUAL(1),
    HOODIE(2);

    private static final SoraVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(SoraVariant::getId)).toArray(SoraVariant[]::new);
    private final int id;

    SoraVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static SoraVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
