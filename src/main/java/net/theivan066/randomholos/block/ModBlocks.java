package net.theivan066.randomholos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.custom.KakuriyoPortalBlock;
import net.theivan066.randomholos.block.custom.ModFlammableRotatedPillarBlock;
import net.theivan066.randomholos.fluid.ModFluids;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.worldgen.tree.MapleTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RandomHolos.MOD_ID);

    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block", ()->
            new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.BAMBOO_WOOD)));

    public static final RegistryObject<Block> MAPLE_LOG = registerBlock("maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> MAPLE_WOOD = registerBlock("maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> MAPLE_PLANKS = registerBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> MAPLE_LEAVES = registerBlock("maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final RegistryObject<Block> MAPLE_SAPLING = registerBlock("maple_sapling",
            () -> new SaplingBlock(new MapleTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> POTTED_MAPLE_SAPLING = BLOCKS.register("potted_maple_sapling",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), MAPLE_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));
    public static final RegistryObject<Block> FALLEN_LEAVES = registerBlock("fallen_leaves", ()->
            new PinkPetalsBlock(BlockBehaviour.Properties.copy(Blocks.PINK_PETALS).sound(SoundType.PINK_PETALS)));

    public static final RegistryObject<LiquidBlock> ELITE_LAVA_BLOCK = BLOCKS.register("elite_lava_block",
            () -> new LiquidBlock(ModFluids.SOURCE_ELITE_LAVA, BlockBehaviour.Properties.copy(Blocks.LAVA).lightLevel(state -> 10).noLootTable()));


    public static final RegistryObject<Block> METEORITE = registerBlock("meteorite", ()->
            new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> CRUDE_METEORITE = registerBlock("crude_meteorite", ()->
            new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> RICH_METEORITE = registerBlock("rich_meteorite", ()->
            new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 10)));
//state -> state.getValue(AlexandriteLampBlock.CLICKED) ? 15 : 0

    public static final RegistryObject<Block> KAKURIYO_PORTAL = registerBlock("kakuriyo_portal",
            () -> new KakuriyoPortalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL).noLootTable().noOcclusion().noCollission()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
