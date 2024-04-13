package user.carboncotton.mc.milldustry.content.mill;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class AllMillClientObjects {

	public static void init(String namespace) {
		BlockEntityRendererFactories.register(
			AllMillObjects.MILL_BLOCK_ENTITY,
			MillBlockEntityRenderer::new
		);
	}
}
