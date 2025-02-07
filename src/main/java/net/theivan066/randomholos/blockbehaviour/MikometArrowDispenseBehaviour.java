package net.theivan066.randomholos.blockbehaviour;

import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.entity.custom.projectile.MikometArrowEntity;

public class MikometArrowDispenseBehaviour extends DefaultDispenseItemBehavior {
    @Override
    protected ItemStack execute(BlockSource source, ItemStack stack) {
        Level level = source.level();
        if (!level.isClientSide) {
            Direction direction = source.state().getValue(DispenserBlock.FACING);
            Vec3 pos = new Vec3(source.pos().getX() + direction.getStepX() * 1.5, source.pos().getY(), source.pos().getZ() + direction.getStepZ() * 1.5);
            MikometArrowEntity projectile = new MikometArrowEntity(ModEntities.MIKOMET_ARROW.get(), pos.x, pos.y, pos.z, level);
            projectile.shoot(direction.getStepX(), direction.getStepY() + 0.1F, direction.getStepZ(), getPower(), getInaccuracy());
            level.addFreshEntity(projectile);
        }
        stack.shrink(1);
        return stack;
    }


    protected float getPower() {
        return 1.1F;
    }

    protected float getInaccuracy() {
        return 1.5F;
    }
}
