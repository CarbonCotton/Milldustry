package user.carboncotton.mc.milldustry.content;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class MillBlockEntity extends BlockEntity implements Inventory, SidedInventory {

	private static final int FUEL_SLOT_INDEX = 0;
	private static final int INPUT_SLOT_INDEX = 1;
	private static final int[] OUTPUT_SLOT_INDEX = new int[]{2,3,4,5,6,7,8,9};
	private DefaultedList<ItemStack> inventory;



	public MillBlockEntity(BlockPos pos, BlockState state) {
		super(AllObjects.KILN_BLOCK_ENTITY, pos, state);

		this.inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);
	}


	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);

		Inventories.readNbt(nbt, this.inventory);
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);

		Inventories.writeNbt(nbt, this.inventory);
	}

	//*** INVENTORY INTERFACE ************************************

	public DefaultedList<ItemStack> getItems() {

		return this.inventory;
	}

	@Override
	public int size() {

		return this.getItems().size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack itemStack : this.getItems()) {
			if(!itemStack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStack(int slot) {

		return this.getItems().get(slot);
	}

	@Override
	public ItemStack removeStack(int slot, int count) {
		ItemStack result = Inventories.splitStack(this.getItems(), slot, count);

		if(!result.isEmpty()) {
			this.markDirty();
		}

		return result;
	}

	@Override
	public ItemStack removeStack(int slot) {

		return Inventories.removeStack(this.getItems(), slot);
	}


	@Override
	public void setStack(int slot, ItemStack stack) {
		this.getItems().set(slot, stack);

		if(stack.getCount() > stack.getMaxCount()) {
			stack.setCount(stack.getMaxCount());
		}
	}

	@Override
	public void clear() {
		this.getItems().clear();
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return true;
	}




	//*** SIDED INVENTORY INTERFACE ******************************


	@Override
	public int[] getAvailableSlots(Direction side) {
		return new int[0];
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return false;
	}


	/**
	 * Items can be extracted from following sides:
	 * + bottom of the block (regardless of facing direction)
	 * + back of the block (opposite of facing direction)
	 *
	 * Items can be extracted from slots in range 2 - 9
	 */
	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {

		// you can't extract from those slots
		if(slot == MillBlockEntity.FUEL_SLOT_INDEX || slot == MillBlockEntity.INPUT_SLOT_INDEX) {
			return false;
		}

		// get back of the block
		Direction blockBack = this.getCachedState().get(MillBlock.FACING).getOpposite();

		return dir == Direction.DOWN || dir == blockBack;

	}
}
