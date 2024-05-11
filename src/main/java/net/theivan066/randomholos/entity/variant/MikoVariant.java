package net.theivan066.randomholos.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum MikoVariant {
    DEFAULT(0),
    MIKOHUKU_MIKO(1),
    PRIVATE_MIKO(2),
    SHOUGATSU_MIKO(3),
    BATTLESUIT_MIKO(4),
    SCHOOL_UNIFORM_MIKO(5),
    DEKAKE_MIKO(6);

    private static final MikoVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MikoVariant::getId)).toArray(MikoVariant[]::new);
    private final int id;

    MikoVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MikoVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
