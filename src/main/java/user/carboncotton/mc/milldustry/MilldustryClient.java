package user.carboncotton.mc.milldustry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import user.carboncotton.mc.milldustry.content.AllClientObjects;

@Environment(EnvType.CLIENT)
public class MilldustryClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AllClientObjects.init("milldustry");
	}
}
