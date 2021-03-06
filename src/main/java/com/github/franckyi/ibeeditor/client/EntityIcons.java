package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class EntityIcons {
    private static final Map<EntityType<?>, EntityIconSupplier> map = new HashMap<>();
    private static final EntityIconSupplier DEFAULT_SUPPLIER = new ItemEntityIconProvider(Items.AIR);

    static {
        map.put(EntityType.BAT, new MobEntityIconProvider(128, 464, EntityType.BAT.getName().getString()));
        map.put(EntityType.BEE, new MobEntityIconProvider(32, 384, EntityType.BEE.getName().getString()));
        map.put(EntityType.BLAZE, new MobEntityIconProvider(48, 464, EntityType.BLAZE.getName().getString()));
        map.put(EntityType.CAT, new MobEntityIconProvider(96, 448, EntityType.CAT.getName().getString()));
        map.put(EntityType.CAVE_SPIDER, new MobEntityIconProvider(96, 480, EntityType.CAVE_SPIDER.getName().getString()));
        map.put(EntityType.CHICKEN, new MobEntityIconProvider(32, 432, EntityType.CHICKEN.getName().getString()));
        map.put(EntityType.COD, new MobEntityIconProvider(80, 160, EntityType.COD.getName().getString()));
        map.put(EntityType.COW, new MobEntityIconProvider(48, 432, EntityType.COW.getName().getString()));
        map.put(EntityType.CREEPER, new MobEntityIconProvider(16, 16, EntityType.CREEPER.getName().getString()));
        map.put(EntityType.DOLPHIN, new MobEntityIconProvider(32, 176, EntityType.DOLPHIN.getName().getString()));
        map.put(EntityType.DONKEY, new MobEntityIconProvider(160, 464, EntityType.DONKEY.getName().getString()));
        map.put(EntityType.DROWNED, new MobEntityIconProvider(0, 176, EntityType.DROWNED.getName().getString()));
        map.put(EntityType.ELDER_GUARDIAN, new MobEntityIconProvider(160, 480, EntityType.ELDER_GUARDIAN.getName().getString()));
        map.put(EntityType.ENDER_DRAGON, new MobEntityIconProvider(64, 32, EntityType.ENDER_DRAGON.getName().getString()));
        map.put(EntityType.ENDERMAN, new MobEntityIconProvider(128, 16, EntityType.ENDERMAN.getName().getString()));
        map.put(EntityType.ENDERMITE, new MobEntityIconProvider(16, 112, EntityType.ENDERMITE.getName().getString()));
        map.put(EntityType.EVOKER, new MobEntityIconProvider(80, 176, EntityType.EVOKER.getName().getString()));
        map.put(EntityType.FOX, new MobEntityIconProvider(48, 368, EntityType.FOX.getName().getString()));
        map.put(EntityType.GHAST, new MobEntityIconProvider(64, 432, EntityType.GHAST.getName().getString()));
        map.put(EntityType.GIANT, new MobEntityIconProvider(32, 448, EntityType.GIANT.getName().getString()));
        map.put(EntityType.GUARDIAN, new MobEntityIconProvider(0, 496, EntityType.GUARDIAN.getName().getString()));
        map.put(EntityType.HORSE, new MobEntityIconProvider(144, 464, EntityType.HORSE.getName().getString()));
        map.put(EntityType.HUSK, new MobEntityIconProvider(32, 128, EntityType.HUSK.getName().getString()));
        map.put(EntityType.ILLUSIONER, new MobEntityIconProvider(16, 496, EntityType.ILLUSIONER.getName().getString()));
        map.put(EntityType.IRON_GOLEM, new MobEntityIconProvider(112, 448, EntityType.IRON_GOLEM.getName().getString()));
        map.put(EntityType.LLAMA, new MobEntityIconProvider(64, 480, EntityType.LLAMA.getName().getString()));
        map.put(EntityType.MAGMA_CUBE, new MobEntityIconProvider(80, 432, EntityType.MAGMA_CUBE.getName().getString()));
        map.put(EntityType.MOOSHROOM, new MobEntityIconProvider(128, 432, EntityType.MOOSHROOM.getName().getString()));
        map.put(EntityType.MULE, new MobEntityIconProvider(80, 480, EntityType.MULE.getName().getString()));
        map.put(EntityType.OCELOT, new MobEntityIconProvider(128, 448, EntityType.OCELOT.getName().getString()));
        map.put(EntityType.PANDA, new MobEntityIconProvider(48, 176, EntityType.PANDA.getName().getString()));
        map.put(EntityType.PARROT, new MobEntityIconProvider(0, 144, EntityType.PARROT.getName().getString()));
        map.put(EntityType.PHANTOM, new MobEntityIconProvider(64, 160, EntityType.PHANTOM.getName().getString()));
        map.put(EntityType.PIG, new MobEntityIconProvider(96, 432, EntityType.PIG.getName().getString()));
        map.put(EntityType.PILLAGER, new MobEntityIconProvider(80, 176, EntityType.PILLAGER.getName().getString()));
        map.put(EntityType.POLAR_BEAR, new MobEntityIconProvider(0, 128, EntityType.POLAR_BEAR.getName().getString()));
        map.put(EntityType.PUFFERFISH, new MobEntityIconProvider(128, 160, EntityType.PUFFERFISH.getName().getString()));
        map.put(EntityType.RABBIT, new MobEntityIconProvider(48, 480, EntityType.RABBIT.getName().getString()));
        map.put(EntityType.RAVAGER, new MobEntityIconProvider(64, 176, EntityType.RAVAGER.getName().getString()));
        map.put(EntityType.SALMON, new MobEntityIconProvider(112, 160, EntityType.SALMON.getName().getString()));
        map.put(EntityType.SHEEP, new MobEntityIconProvider(144, 432, EntityType.SHEEP.getName().getString()));
        map.put(EntityType.SHULKER, new MobEntityIconProvider(80, 32, EntityType.SHULKER.getName().getString()));
        map.put(EntityType.SILVERFISH, new MobEntityIconProvider(144, 16, EntityType.SILVERFISH.getName().getString()));
        map.put(EntityType.SKELETON, new MobEntityIconProvider(160, 432, EntityType.SKELETON.getName().getString()));
        map.put(EntityType.SKELETON_HORSE, new MobEntityIconProvider(176, 464, EntityType.SKELETON_HORSE.getName().getString()));
        map.put(EntityType.SLIME, new MobEntityIconProvider(48, 448, EntityType.SLIME.getName().getString()));
        map.put(EntityType.SNOW_GOLEM, new MobEntityIconProvider(144, 448, EntityType.SNOW_GOLEM.getName().getString()));
        map.put(EntityType.SPIDER, new MobEntityIconProvider(64, 448, EntityType.SPIDER.getName().getString()));
        map.put(EntityType.SQUID, new MobEntityIconProvider(176, 432, EntityType.SQUID.getName().getString()));
        map.put(EntityType.STRAY, new MobEntityIconProvider(80, 496, EntityType.STRAY.getName().getString()));
        map.put(EntityType.TRADER_LLAMA, new MobEntityIconProvider(64, 480, EntityType.TRADER_LLAMA.getName().getString()));
        map.put(EntityType.TROPICAL_FISH, new MobEntityIconProvider(144, 160, EntityType.TROPICAL_FISH.getName().getString()));
        map.put(EntityType.TURTLE, new MobEntityIconProvider(32, 160, EntityType.TURTLE.getName().getString()));
        map.put(EntityType.VEX, new MobEntityIconProvider(112, 496, EntityType.VEX.getName().getString()));
        map.put(EntityType.VILLAGER, new MobEntityIconProvider(160, 608, EntityType.VILLAGER.getName().getString()));
        map.put(EntityType.VINDICATOR, new MobEntityIconProvider(128, 496, EntityType.VINDICATOR.getName().getString()));
        map.put(EntityType.WANDERING_TRADER, new MobEntityIconProvider(64, 368, EntityType.WANDERING_TRADER.getName().getString()));
        map.put(EntityType.WITCH, new MobEntityIconProvider(0, 464, EntityType.WITCH.getName().getString()));
        map.put(EntityType.WITHER, new MobEntityIconProvider(96, 464, EntityType.WITHER.getName().getString()));
        map.put(EntityType.WITHER_SKELETON, new MobEntityIconProvider(16, 464, EntityType.WITHER_SKELETON.getName().getString()));
        map.put(EntityType.WOLF, new MobEntityIconProvider(16, 448, EntityType.WOLF.getName().getString()));
        map.put(EntityType.ZOMBIE, new MobEntityIconProvider(32, 448, EntityType.ZOMBIE.getName().getString()));
        map.put(EntityType.ZOMBIE_HORSE, new MobEntityIconProvider(0, 480, EntityType.ZOMBIE_HORSE.getName().getString()));
        //map.put(EntityType.ZOMBIE_PIGMAN, new MobEntityIconProvider(160, 496, EntityType.ZOMBIE_PIGMAN.getName().getString()));
        map.put(EntityType.ZOMBIE_VILLAGER, new MobEntityIconProvider(160, 288, EntityType.ZOMBIE_VILLAGER.getName().getString()));
        map.put(EntityType.ARMOR_STAND, new ItemEntityIconProvider(Items.ARMOR_STAND));
        map.put(EntityType.BOAT, new ItemEntityIconProvider(Items.OAK_BOAT));
        map.put(EntityType.END_CRYSTAL, new ItemEntityIconProvider(Items.END_CRYSTAL));
        map.put(EntityType.ITEM_FRAME, new ItemEntityIconProvider(Items.ITEM_FRAME));
        map.put(EntityType.FIREBALL, new ItemEntityIconProvider(Items.FIRE_CHARGE));
        map.put(EntityType.MINECART, new ItemEntityIconProvider(Items.MINECART));
        map.put(EntityType.CHEST_MINECART, new ItemEntityIconProvider(Items.CHEST_MINECART));
        map.put(EntityType.COMMAND_BLOCK_MINECART, new ItemEntityIconProvider(Items.COMMAND_BLOCK_MINECART));
        map.put(EntityType.FURNACE_MINECART, new ItemEntityIconProvider(Items.FURNACE_MINECART));
        map.put(EntityType.HOPPER_MINECART, new ItemEntityIconProvider(Items.HOPPER_MINECART));
        map.put(EntityType.SPAWNER_MINECART, new ItemEntityIconProvider(Items.SPAWNER));
        map.put(EntityType.TNT_MINECART, new ItemEntityIconProvider(Items.TNT_MINECART));
        map.put(EntityType.PAINTING, new ItemEntityIconProvider(Items.PAINTING));
        map.put(EntityType.TNT, new ItemEntityIconProvider(Items.TNT));
        map.put(EntityType.PLAYER, new ItemEntityIconProvider(Items.PLAYER_HEAD));
        //1.16
        map.put(EntityType.HOGLIN, new MobEntityIconProvider(0, 400, EntityType.HOGLIN.getName().getString()));
        map.put(EntityType.PIGLIN, new MobEntityIconProvider(112, 384, EntityType.PIGLIN.getName().getString()));
        map.put(EntityType.ZOMBIFIED_PIGLIN, new MobEntityIconProvider(160, 496, EntityType.ZOMBIFIED_PIGLIN.getName().getString()));
        map.put(EntityType.STRIDER, new MobEntityIconProvider(48, 512, EntityType.STRIDER.getName().getString()));
        map.put(EntityType.ZOGLIN, new MobEntityIconProvider(64, 512, EntityType.ZOGLIN.getName().getString()));
    }

    public static TexturedButton createTexturedButtonForEntity(EntityType<?> entityType) {
        return map.getOrDefault(entityType, DEFAULT_SUPPLIER).get();
    }

    private interface EntityIconSupplier extends Supplier<TexturedButton> {
    }

    private static class MobEntityIconProvider implements EntityIconSupplier {
        private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(IBEEditorMod.MODID, "textures/gui/entities.png");

        private final int u, v;
        private final String name;

        public MobEntityIconProvider(int u, int v, String name) {
            this.u = u;
            this.v = v;
            this.name = name;
        }

        @Override
        public TexturedButton get() {
            return new TexturedButton(RESOURCE_LOCATION, 192, 592, u, v, name);
        }
    }

    private static class ItemEntityIconProvider implements EntityIconSupplier {
        private final Item item;

        private ItemEntityIconProvider(Item item) {
            this.item = item;
        }

        @Override
        public TexturedButton get() {
            return new TexturedButton(new ItemStack(item));
        }
    }
}
