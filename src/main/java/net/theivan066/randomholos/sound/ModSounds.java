package net.theivan066.randomholos.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RandomHolos.MOD_ID);

    public static final RegistryObject<SoundEvent> SORA_HURT = registerSoundEvents("sora_hurt");
    public static final RegistryObject<SoundEvent> SORA_AMBIENT = registerSoundEvents("sora_ambient");
    public static final RegistryObject<SoundEvent> SORA_TEKIDANE = registerSoundEvents("sora_tekidane");
    public static final RegistryObject<SoundEvent> SORA_DEATH = registerSoundEvents("sora_death");
    public static final RegistryObject<SoundEvent> ROBOGUN_SHOOT = registerSoundEvents("robogun_shoot");
    public static final RegistryObject<SoundEvent> MIKO_HURT = registerSoundEvents("miko_hurt");
    public static final RegistryObject<SoundEvent> MIKO_AMBIENT = registerSoundEvents("miko_ambient");
    public static final RegistryObject<SoundEvent> MIKO_DEATH = registerSoundEvents("miko_death");
    public static final RegistryObject<SoundEvent> AZKI_HURT = registerSoundEvents("azki_hurt");
    public static final RegistryObject<SoundEvent> AZKI_AMBIENT = registerSoundEvents("azki_ambient");
    public static final RegistryObject<SoundEvent> AZKI_DEATH = registerSoundEvents("azki_death");
    public static final RegistryObject<SoundEvent> SUISEI_HURT = registerSoundEvents("suisei_hurt");
    public static final RegistryObject<SoundEvent> SUISEI_AMBIENT = registerSoundEvents("suisei_ambient");
    public static final RegistryObject<SoundEvent> SUISEI_DEATH = registerSoundEvents("suisei_death");
    public static final RegistryObject<SoundEvent> ROBOCO_HURT = registerSoundEvents("roboco_hurt");
    public static final RegistryObject<SoundEvent> ROBOCO_AMBIENT = registerSoundEvents("roboco_ambient");
    public static final RegistryObject<SoundEvent> ROBOCO_DEATH = registerSoundEvents("roboco_death");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = new ResourceLocation(RandomHolos.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
