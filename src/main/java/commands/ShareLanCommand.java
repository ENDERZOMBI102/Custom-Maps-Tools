package commands;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.enderzombi102.cmt.CustomMapsTools;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ShareLanCommand extends CommandBase {

	@Override
	public String getName() {
		return "sharetolan";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/sharetolan <gamemode> <cheatsenabled> [port]";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		// check if the integrated server is running
		// TODO: this check will not work on a dedicated server
		if ( Minecraft.getMinecraft().isIntegratedServerRunning() ) {
			
			// check if the server is shared (its a private method, so we need reflection
			if ( (boolean) ObfuscationReflectionHelper.getPrivateValue(IntegratedServer.class, (IntegratedServer) server, "isPublic") ) {
				// server is already shared
				throw new CommandException("The world is already open!");
			} else {
				// check the parameters
				if (args.length < 2) {
					throw new WrongUsageException( this.getUsage(sender) );
				}
				// check parameter 1
				if ( GameType.parseGameTypeWithDefault(args[0], GameType.NOT_SET) == GameType.NOT_SET ) {
					// invalid gamemode
					throw new WrongUsageException("Invalid parameter "+args[0]+". expected gamemode name");
				}
				// parameter 2
				if ( !args[1].toLowerCase().equals("true") && !args[1].toLowerCase().equals("false") ) {
					throw new WrongUsageException("Invalid parameter "+args[1]+". expected boolean (true, false)");
				}
				// parameter 3
				if (args.length == 3) {
					try {
						int port = Integer.parseInt(args[2]);
						if (port > 25565 || port < 10000 ) {
							throw new WrongUsageException("Invalid parameter "+args[2]+". expecting nothing or valuebetween 10000 and 25565");
						}
					} catch(NumberFormatException e) {
						throw new WrongUsageException("Invalid parameter "+args[2]+". expected an integer");
					}
				}
				// isn't shared, lets share it!
				CustomMapsTools.STweaker.shareToLan( GameType.getByName(args[0]), parseBoolean(args[1]), args.length == 3 ? parseInt(args[3]) : 25565 );
			}
		} else {
			// integrated server isn't running
			throw new CommandException("this command can only work on single player worlds");
		}
		notifyCommandListener(sender, this, "Sucessfully opened world to lan", (Object) null);
		//net.minecraft.command.CommandFunction
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        ArrayList<String> comp = new ArrayList<>();
		if (args.length == 1) {
			comp.add("adventure");
			comp.add("survival");
			comp.add("creative");
			comp.add("spectator");
		} else if (args.length == 2) {
			comp.add("true");
			comp.add("false");
		} else if (args.length == 3) {
			comp.add("25565");
		}
		return comp;
    }
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
		return sender.canUseCommand(2, this.getName() );
    }

}
