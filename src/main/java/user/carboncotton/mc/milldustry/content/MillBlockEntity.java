package user.carboncotton.mc.milldustry.content;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class MillBlockEntity extends BlockEntity {

	private static final int FUEL_SLOT_INDEX = 0;
	private static final int INPUT_SLOT_INDEX = 1;
	private static final int[] OUTPUT_SLOT_INDEX = new int[]{2,3,4,5,6,7,8,9};
	private DefaultedList<ItemStack> inventory;



	public MillBlockEntity(BlockPos pos, BlockState state) {
		super(AllObjects.KILN_BLOCK_ENTITY, pos, state);

		this.inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);
	}
}
