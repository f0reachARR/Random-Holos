package net.theivan066.randomholos.sound;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, RandomHolos.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> SORA_HURT = registerSoundEvents("sora_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> SORA_AMBIENT = registerSoundEvents("sora_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> SORA_TEKIDANE = registerSoundEvents("sora_tekidane");
    public static final DeferredHolder<SoundEvent, SoundEvent> SORA_DEATH = registerSoundEvents("sora_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOGUN_SHOOT = registerSoundEvents("robogun_shoot");
    public static final DeferredHolder<SoundEvent, SoundEvent> MIKO_HURT = registerSoundEvents("miko_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> MIKO_AMBIENT = registerSoundEvents("miko_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> MIKO_DEATH = registerSoundEvents("miko_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> AZKI_HURT = registerSoundEvents("azki_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> AZKI_AMBIENT = registerSoundEvents("azki_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> AZKI_DEATH = registerSoundEvents("azki_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> SUISEI_HURT = registerSoundEvents("suisei_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> SUISEI_AMBIENT = registerSoundEvents("suisei_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> SUISEI_DEATH = registerSoundEvents("suisei_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOCO_HURT = registerSoundEvents("roboco_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOCO_AMBIENT = registerSoundEvents("roboco_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOCO_DEATH = registerSoundEvents("roboco_death");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
