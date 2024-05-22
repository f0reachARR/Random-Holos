package net.theivan066.randomholos.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum RobocoVariant {
    DEFAULT(0),
    HOODIE_ROBOCO(1),
    SPORT_ROBOCO(2),
    COAT_ROBOCO(3),
    NIGHTY_DRESS_ROBOCO(4),
    SUMMER_ROBOCO(5);

    private static final RobocoVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(RobocoVariant::getId)).toArray(RobocoVariant[]::new);
    private final int id;

    RobocoVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static RobocoVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
