package net.theivan066.randomholos.client;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.particle.GuesserPinParticles;
import net.theivan066.randomholos.particle.ModParticles;

@EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class GameClientBusEvents {
    //Particle
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.GUESSER_PIN_PARTICLES.get(), GuesserPinParticles.Provider::new);
    }

}
