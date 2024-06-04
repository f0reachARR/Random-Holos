package net.theivan066.randomholos.entity.custom.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class MikometArrow extends MikometArrowEntity{
    public MikometArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public MikometArrow(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.ARROW, pX, pY, pZ, pLevel);
    }

    public MikometArrow(Level pLevel, LivingEntity pShooter) {
        super(EntityType.ARROW, pShooter, pLevel);
    }
}
