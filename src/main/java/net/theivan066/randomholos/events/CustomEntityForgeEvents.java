package net.theivan066.randomholos.events;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.MikoEntity;

import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CustomEntityForgeEvents {

    //miko あえんびえん death
    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof MikoEntity) {
            DamageSource damageSource = event.getSource();
            Entity miko = event.getEntity();
            boolean fireEnchants = false;
            if (damageSource.is(DamageTypes.PLAYER_ATTACK) && !damageSource.isIndirect()) {
                Player player = (Player) damageSource.getEntity();
                assert player != null;
                Map<Enchantment, Integer> map = player.getMainHandItem().getAllEnchantments();
                fireEnchants = map.get(Enchantments.FIRE_ASPECT) > 0 || map.get(Enchantments.FLAMING_ARROWS) > 0;
            }
            boolean diedFromAenbien = damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.LAVA) || damageSource.is(DamageTypes.FIREBALL) || damageSource.is(DamageTypes.PLAYER_EXPLOSION) || miko.isOnFire();
            if (fireEnchants && !miko.level().isClientSide) {
                Player player = (Player) damageSource.getEntity();
                player.sendSystemMessage(Component.translatable("messages.randomholos.aenbien"));
                miko.playSound(SoundEvents.ENDERMAN_DEATH, 1, 1);
            } else if (diedFromAenbien){
                List<? extends Player> players = miko.level().players();
                for (Player player : players) {
                    if (!miko.level().isClientSide) {
                        player.sendSystemMessage(Component.translatable("messages.randomholos.aenbien"));
                    }
                    miko.playSound(SoundEvents.ENDERMAN_DEATH, 1, 1);
                }
            }
        }
    }
}

