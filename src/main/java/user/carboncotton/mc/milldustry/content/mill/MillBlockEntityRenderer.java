package user.carboncotton.mc.milldustry.content.mill;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;

@Environment(EnvType.CLIENT)
public class MillBlockEntityRenderer implements BlockEntityRenderer<MillBlockEntity> {

	private final BlockEntityRendererFactory.Context context;

	public MillBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
		this.context = context;
	}

	@Override
	public void render(MillBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		// there is no reason to render if milling block is there
		var millingBlock = blockEntity.getMillingBlock();
		if(millingBlock.isEmpty()) {
			return;
		}

		matrices.push();

		// move milling block
		matrices.translate(0.5, -0.1, 0.5);

		// expand milling block
		matrices.scale(3.0f, 3.0f, 3.0f);

		// rotate milling block if condition is satisfied
		// TODO: implement actual condition
		if(true) {
			matrices.multiply(
				RotationAxis.POSITIVE_Y.rotationDegrees((blockEntity.getWorld().getTime() + tickDelta) * 4)
			);
		}

		// TODO: find reason why light doesn't work
		MinecraftClient.getInstance().getItemRenderer().renderItem(
			millingBlock,
			ModelTransformationMode.GROUND,
			10,
			overlay,
			matrices,
			vertexConsumers,
			blockEntity.getWorld(),
			0
		);

		matrices.pop();
	}
}
