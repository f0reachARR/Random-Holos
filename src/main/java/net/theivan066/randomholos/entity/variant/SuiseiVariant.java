package net.theivan066.randomholos.entity.variant;

import java.util.Arrays;
import java.util.Comparator;
public enum SuiseiVariant {
    DEFAULT(0),
    KOJINSEI(1),
    NORMAL_IDOL(2),
    SAILOR(3),
    WAR_MAID(4),
    IKEMEN_NI_NACCHATTA(5);

    private static final SuiseiVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(SuiseiVariant::getId)).toArray(SuiseiVariant[]::new);
    private final int id;

    SuiseiVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static SuiseiVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
