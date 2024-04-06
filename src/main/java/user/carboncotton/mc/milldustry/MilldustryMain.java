package user.carboncotton.mc.milldustry;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.carboncotton.mc.milldustry.content.AllObjects;

public class MilldustryMain implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("milldustry");

	@Override
	public void onInitialize() {

		AllObjects.init("milldustry");
	}
}