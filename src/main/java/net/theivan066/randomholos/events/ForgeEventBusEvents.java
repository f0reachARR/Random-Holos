package net.theivan066.randomholos.events;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.enchantment.ModEnchantments;
import net.theivan066.randomholos.entity.custom.MikoEntity;
import net.theivan066.randomholos.item.custom.GunItem;
import net.theivan066.randomholos.item.custom.MikoBowItem;
import net.theivan066.randomholos.item.custom.MikometBowItem;
import net.theivan066.randomholos.item.custom.RobosniperItem;

import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEventBusEvents {

    //bow FOVs
    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() instanceof MikoBowItem) {
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / 20.0F;
            if (deltaTicks > 1.0F) {
                deltaTicks = 1.0F;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1.0F - deltaTicks * 0.15F;
            event.setNewFovModifier(fovModifier);
        }
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() instanceof MikometBowItem) {
            int fastDraw = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FAST_DRAW.get(), event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND));
            float i = fastDraw * 5;
            float totalDrawTime = 40 - i;
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / totalDrawTime;
            if (deltaTicks > 1.0F) {
                deltaTicks = 1.0F;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1.0F - deltaTicks * 0.5F;
            event.setNewFovModifier(fovModifier);
        }
        if (event.getPlayer().getOffhandItem().getItem() instanceof RobosniperItem) {
            event.setNewFovModifier(0.2F);
        }
    }

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

    //tried changing crosshair
    private static final ResourceLocation BOW_CROSSHAIR_ICON = new ResourceLocation(RandomHolos.MOD_ID, "textures/gui/icons.png");
    @SubscribeEvent
    public void onUseMikometBow(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().id().equals(ResourceLocation.of("minecraft:textures/gui/icons.png", ':'))) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (player != null && player.getMainHandItem().getItem() instanceof MikometBowItem) {
                event.setCanceled(true);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, BOW_CROSSHAIR_ICON);
                int screenWidth = event.getWindow().getGuiScaledWidth();
                int screenHeight = event.getWindow().getGuiScaledHeight();
                int x = (screenWidth - 15) / 2; // Adjust for 16x16 crosshair
                int y = (screenHeight - 15) / 2;
                event.getGuiGraphics().blit(BOW_CROSSHAIR_ICON, x, y, 0,0,15,15,256,256);
            } else {
                event.setCanceled(false);
            }
        }
    }

//    //gun shoot
//    @SubscribeEvent
//    public void useGun(PlayerInteractEvent.LeftClickEmpty event) {
//        Player player = event.getEntity();
//        Item item = player.getUseItem().getItem();
//        if (item instanceof GunItem) {
//            ((GunItem) item).fireCheck(event.getLevel(), player, player.getUsedItemHand(), (GunItem) item);
//        }
//    }
}

