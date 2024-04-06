package user.carboncotton.mc.milldustry.content;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class MillBlock extends BlockWithEntity {

	public static final DirectionProperty FACING;

	static {
		FACING = FacingBlock.FACING;
	}


	protected MillBlock(Settings settings) {
		super(settings);

		this.setDefaultState(
			this.getStateManager().getDefaultState()
				.with(FACING, Direction.UP)
		);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.FACING);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos position, BlockState state) {
		return new MillBlockEntity(position, state);
	}


	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {

		var facing = ctx.getPlayerLookDirection().getOpposite();

		// mill cannot face down, so we will switch it to up
		if(facing == Direction.DOWN) {
			facing = Direction.UP;
		}

		return this.getDefaultState().with(FACING, facing);
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {

		return state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {

		return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
	}
}
