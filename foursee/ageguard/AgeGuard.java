package foursee.ageguard;

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
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="AgeTrack", name="Myst Age Security", version="0.1.0", dependencies = "after:Mystcraft")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)
public class AgeGuard {
	public static final String ID = "AgeGuard";

        // The instance of your mod that Forge uses.
	@Instance("AgeGuard")
	public static AgeGuard instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="foursee.ageguard.CommonProxy", serverSide="foursee.ageguard.CommonProxy")
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
		GameRegistry.registerPlayerTracker(new PlayerTrackerHandler(logger));
		logger.info("Registered Player Tracker Handler with Forge");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
}