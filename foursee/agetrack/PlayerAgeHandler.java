package foursee.agetrack;

import java.util.Locale;
import java.util.Date;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayerMP;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;

public final class PlayerAgeHandler {

	private Logger logger;
	private Date time;

	public PlayerAgeHandler(Logger logger) {
		this.logger = logger;
		// Initialize
	}
}
