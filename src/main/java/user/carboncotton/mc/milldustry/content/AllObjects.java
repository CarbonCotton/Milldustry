package user.carboncotton.mc.milldustry.content;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AllObjects {

	public static Block MILL_BLOCK;

	public static Item MILL_BLOCK_ITEM;

	public static BlockEntityType<MillBlockEntity> MILL_BLOCK_ENTITY;

	public static void init(String namespace) {
		final var MillId = new Identifier(namespace, "mill");

		MILL_BLOCK = Registry.register(
			Registries.BLOCK,
			MillId,
			new MillBlock(
				AbstractBlock.Settings.create()
					.mapColor(MapColor.STONE_GRAY)
					.instrument(Instrument.BASEDRUM)
					.requiresTool()
					.strength(3.5F)
			)
		);

		MILL_BLOCK_ITEM = Registry.register(
			Registries.ITEM,
			MillId,
			new BlockItem(MILL_BLOCK, new Item.Settings())
		);

		MILL_BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			MillId,
			FabricBlockEntityTypeBuilder.create(MillBlockEntity::new, MILL_BLOCK).build()
		);

	}
}
