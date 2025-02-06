package net.theivan066.randomholos.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.theivan066.randomholos.RandomHolos;

public class ModKeyBindings {
    public static final ModKeyBindings INSTANCE = new ModKeyBindings();

    private ModKeyBindings() {
    }

    private static final String CATEGORY = "key.categories." + RandomHolos.MOD_ID;

    public final KeyMapping ABILITY_KEY = new KeyMapping(
            "key.randomholos.ability",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_G, -1),
            CATEGORY
    );

    public final KeyMapping SECONDARY_ABILITY_KEY = new KeyMapping(
            "key.randomholos.secondary_ability",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_V, -1),
            CATEGORY
    );
}
