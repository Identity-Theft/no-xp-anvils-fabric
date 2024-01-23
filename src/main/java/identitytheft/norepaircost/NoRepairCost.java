package identitytheft.norepaircost;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoRepairCost implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("no-repair-cost");

	@Override
	public void onInitialize() {
		LOGGER.info("!");
	}
}