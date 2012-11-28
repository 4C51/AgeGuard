package foursee.ageguard;

import java.util.logging.Logger;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import cpw.mods.fml.common.IPlayerTracker;

import xcompwiz.mystcraft.AgeData;

public class PlayerTrackerHandler implements IPlayerTracker {

	private Logger logger;
	private String[] dim = {"Nether", "Overworld", "The End"};
	private Date time;

	public PlayerTrackerHandler (Logger logger) {
		this.logger = logger;
	}
	
	public void onPlayerLogin(EntityPlayer player) {
		String username = player.username;
		int dimension = player.dimension;
		String agename = getAgeName(dimension, player.worldObj);
		int[] pos = {(int)player.posX, (int)player.posY, (int)player.posZ};
		
		logger.info(username + " logged into " + agename + " [" + dimension + "] at " + String.format("%s %s %s", pos[0], pos[1], pos[2]));
		signGuestbook(username,"login",agename,dimension,pos);
	}

	public void onPlayerLogout(EntityPlayer player) {
		// Not Used
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		String username = player.username;
		int dimension = player.dimension;
		String agename = getAgeName(dimension, player.worldObj);
		int[] pos = {(int)player.posX, (int)player.posY, (int)player.posZ};
		
		logger.info(username + " entered " + agename + " [" + dimension + "] at " + String.format("%s %s %s", pos[0], pos[1], pos[2]));
		signGuestbook(username,"teleport",agename,dimension,pos);
	}

	public void onPlayerRespawn(EntityPlayer player) {
		// Not Used
	}
	
	private void signGuestbook(String username, String method, String agename, int dimension, int[] pos) {
		time = new Date();
		String ageTrack = String.format("%s,%s,%s,%s,%s,%s,%s,%s", username, method, dimension, agename, pos[0], pos[1], pos[2], (int)(time.getTime()/1000));
		
		try {
			FileWriter agelog = new FileWriter("ageGuestbook.log", true);
			agelog.write(ageTrack + "\n");
			agelog.close();
		} catch (IOException e){}
	}

	private String getAgeName(int dimension, World world) {
		String agename;
		
		if (dimension > 1) {
			agename = AgeData.getAge(world, dimension).agename;
		} else {
			agename = dim[dimension + 1];
		}
		
		return agename;
	}
}
