package user.carboncotton.mc.milldustry.content;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class MillBlockEntity extends BlockEntity {

	public MillBlockEntity(BlockPos pos, BlockState state) {
		super(AllObjects.KILN_BLOCK_ENTITY, pos, state);
	}
}
