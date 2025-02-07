package net.theivan066.randomholos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.custom.*;
import net.theivan066.randomholos.fluid.ModFluids;
import net.theivan066.randomholos.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(RandomHolos.MOD_ID);

    public static final DeferredBlock<Block> TEST_BLOCK = registerBlock("test_block", () ->
            new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.BAMBOO_WOOD)));

    public static final DeferredBlock<Block> MAPLE_LOG = registerBlock("maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> MAPLE_WOOD = registerBlock("maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final DeferredBlock<Block> MAPLE_PLANKS = registerBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
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
    public static final DeferredBlock<Block> MAPLE_LEAVES = registerBlock("maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
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
    public static final DeferredBlock<Block> MAPLE_SAPLING = registerBlock("maple_sapling",
            () -> new SaplingBlock(TreeGrower.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<Block> POTTED_MAPLE_SAPLING = BLOCKS.register("potted_maple_sapling",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), MAPLE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)));
    public static final DeferredBlock<Block> FALLEN_LEAVES = registerBlock("fallen_leaves", () ->
            new PinkPetalsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS).sound(SoundType.PINK_PETALS)));

    public static final DeferredBlock<LiquidBlock> ELITE_LAVA_BLOCK = BLOCKS.register("elite_lava_block",
            () -> new LiquidBlock(ModFluids.SOURCE_ELITE_LAVA.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).lightLevel(state -> 10).noLootTable()));
    public static final DeferredBlock<Block> HUMIDIFIER = registerBlock("humidifier", () ->
            new HumidifierBlock(BlockBehaviour.Properties.of().strength(0.6f).sound(SoundType.AMETHYST_CLUSTER).noOcclusion()));

    public static final DeferredBlock<Block> METEORITE = registerBlock("meteorite", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> CRUDE_METEORITE = registerBlock("crude_meteorite", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> RICH_METEORITE = registerBlock("rich_meteorite", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 10)));
    public static final DeferredBlock<Block> TOURMALINE_ORE = registerBlock("tourmaline_ore", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 1)));
    public static final DeferredBlock<Block> DEEPSLATE_TOURMALINE_ORE = registerBlock("deepslate_tourmaline_ore", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 1)));
    public static final DeferredBlock<Block> TOURMALINE_BlOCK = registerBlock("tourmaline_block", () ->
            new TourmalineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel(state -> 8)));


    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(6.0f, 7.0f).requiresCorrectToolForDrops().sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> MANUFACTURING_TABLE = registerBlock("manufacturing_table", () ->
            new ManufacturingTableBlock(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.STONE).lightLevel(state -> 12).noOcclusion()));
    public static final DeferredBlock<Block> CHIVES_CROP = BLOCKS.register("chives_crop",
            () -> new ChivesCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).noCollission().noOcclusion()));
    public static final DeferredBlock<Block> SCALLION_CROP = BLOCKS.register("scallion_crop",
            () -> new ScallionCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).noCollission().noOcclusion()));
    public static final DeferredBlock<Block> CHINESE_CABBAGE_CROP = BLOCKS.register("chinese_cabbage_crop",
            () -> new ChineseCabbageCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).noCollission().noOcclusion()));
    public static final DeferredBlock<Block> CHILI_BUSH = BLOCKS.register("chili_bush",
            () -> new ChiliBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).noCollission().noOcclusion()));
    public static final DeferredBlock<Block> COOKING_POT = registerBlock("cooking_pot", () ->
            new CookingPotBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(0.5F).noOcclusion()));
    public static final DeferredBlock<Block> KIMCHI_HOTPOT = registerBlock("kimchi_hotpot", () ->
            new KimchiHotpotBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(0.5F).noOcclusion()));

    public static final DeferredBlock<Block> KAKURIYO_PORTAL = registerBlock("kakuriyo_portal",
            () -> new KakuriyoPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL).noLootTable().noOcclusion().noCollission()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<BlockItem> registerBlockItem(String name, DeferredBlock<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
