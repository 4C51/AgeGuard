package foursee.agetrack;

import java.util.logging.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import net.minecraftforge.event.EventBus;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="AgeTrack", name="Myst Age Tracker", version="0.0.1")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)
public class AgeTrack {
	public static final String ID = "AgeTrack";

        // The instance of your mod that Forge uses.
	@Instance("AgeTrack")
	public static AgeTrack instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="foursee.agetrack.CommonProxy", serverSide="foursee.agetrack.CommonProxy")
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		logger = Logger.getLogger(ID);
		logger.setParent(FMLLog.getLogger());
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		MinecraftForge.EVENT_BUS.register(new PlayerAgeHandler(logger));
		logger.info("Registered Age Tracker with Forge");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
}