package user.carboncotton.mc.milldustry.content.mill;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class MillBlock extends BlockWithEntity implements Waterloggable {

	public static final DirectionProperty FACING;

	public static final BooleanProperty WATERLOGGED;

	static {
		FACING 		= FacingBlock.FACING;
		WATERLOGGED = Properties.WATERLOGGED;
	}


	protected MillBlock(Settings settings) {
		super(settings);

		this.setDefaultState(
			this.getStateManager().getDefaultState()
				.with(FACING, Direction.UP)
				.with(WATERLOGGED, false)
		);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.FACING, WATERLOGGED);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos position, BlockState state) {
		return new MillBlockEntity(position, state);
	}


	@Override
	public <T extends BlockEntity>BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, AllMillObjects.MILL_BLOCK_ENTITY, MillBlockEntity::tick);
	}


	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		var facing = ctx.getPlayerLookDirection().getOpposite();

		// mill cannot face down, so we will switch it to up
		if(facing == Direction.DOWN) {
			facing = Direction.UP;
		}

		return this.getDefaultState()
			.with(FACING, facing)
			.with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {

		return state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {

		return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}


}
