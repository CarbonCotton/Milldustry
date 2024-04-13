package user.carboncotton.mc.milldustry.content;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import user.carboncotton.mc.milldustry.content.mill.AllMillClientObjects;

@Environment(EnvType.CLIENT)
public class AllClientObjects {

	public static void init(String namespace) {
		AllMillClientObjects.init(namespace);
	}
}
