package user.carboncotton.mc.milldustry.content.mill;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class MillBlockEntityRenderer implements BlockEntityRenderer<MillBlockEntity> {

	public MillBlockEntityRenderer(BlockEntityRendererFactory.Context context) {}

	@Override
	public void render(MillBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
	}
}
