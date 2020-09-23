package commands;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.enderzombi102.cmt.ConfigHandler;
import com.enderzombi102.cmt.CustomMapsTools;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.server.command.CommandTreeBase;

public class WindowCommand extends CommandTreeBase {

	public WindowCommand() {
		// subcommand: position
		this.addSubcommand( new CommandBase() {

			@Override
			public String getName() {
				return "position";
			}

			@Override
			public String getUsage(ICommandSender sender) {
				return "position <x> <y>";
			}
			
			@Override
			public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
				return new ArrayList<>();
			}

			@Override
			public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
				if ( args.length < 2 && args.length > 2) throw new WrongUsageException( this.getUsage(sender) );
				int x, y;
				try {
					x = Integer.parseInt( args[0] );
					y = Integer.parseInt( args[1] );
				} catch(NumberFormatException e) {
					throw new WrongUsageException( this.getUsage(sender) );
				}
				CustomMapsTools.CTweaker.updatePosition(x, y);
			}
			
		});
		// subcommand: icon
		this.addSubcommand( new CommandBase() {

			@Override
			public String getName() {
				return "icon";
			}

			@Override
			public String getUsage(ICommandSender sender) {
				return "icon <icontype> <domain> <path>";
			}
			
			public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
				return new ArrayList<>();
			}

			@Override
			public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
				if ( args.length < 3 && args.length > 3 ) throw new WrongUsageException( this.getUsage(sender) );
				
				String text = "";
				if ( args[0].equals("window") ) {
					ConfigHandler.ConfigData.iconPath16 = args[1] + ":" + args[2];
					text = "updated game window icon";
				} else if ( args[0].equals("taskbar") ) {
					ConfigHandler.ConfigData.iconPath32 = args[1] + ":" + args[2];
					text = "updated game taskbar icon";
				} else {
					throw new CommandException("Invalid parameter "+args[0]+". expected \"taskbar\" or \"window\"");
				}
				CustomMapsTools.CTweaker.updateIcon();
				notifyCommandListener(sender, this, text, (Object)null);
				
			}
			
		});
		// subcommand: fullscreen
		this.addSubcommand( new CommandBase() {
			@Override
			public String getName() {
				return "fullscreen";
			}

			@Override
			public String getUsage(ICommandSender sender) {
				return "fullscreen";
			}
			
			public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
				return new ArrayList<>();
			}

			@Override
			public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
				if ( args.length > 0 ) throw new WrongUsageException( this.getUsage(sender) );
				
				Minecraft.getMinecraft().toggleFullscreen();
				notifyCommandListener(sender, this, "toggled fullscreen", (Object)null);
				
			}
		});
	}
	
	
	@Override
	public List<String> getAliases() {
		ArrayList<String> aliases = new ArrayList<>();
		aliases.add("win");
		return aliases;
	}
	
	
	@Override
	public String getName() {
		return "window";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/window <subcommand> ...";
	}

}
