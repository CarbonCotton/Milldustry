package user.carboncotton.mc.milldustry.content.mill;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;

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


		MinecraftClient.getInstance().getItemRenderer().renderItem(
			millingBlock,
			ModelTransformationMode.GROUND,
			light,
			overlay,
			matrices,
			vertexConsumers,
			blockEntity.getWorld(),
			0
		);
	}

}
