package commands;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import scala.actors.threadpool.Arrays;

public abstract class Command extends CommandBase {
	

	/*
	 * this array holds the auto completions
	 */
	protected String [] completitions;
	
	
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender.getCommandSenderEntity() == null) {
			// only command blocks don't have an entity defined
			this.onCommandBlockExec(sender, args);
		} else if (sender.getCommandSenderEntity() instanceof EntityPlayerSP || sender.getCommandSenderEntity() instanceof EntityPlayerMP) {
			this.onPlayerExec(sender, args);
		}
		this.onExec(sender, args);
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return getListOfStringsMatchingLastWord( args, Arrays.asList(this.completitions) );
    }
	
	/*
	 * this is executed if the command is sent by a command block
	 */
	protected abstract void onCommandBlockExec(ICommandSender sender, String[] args) throws CommandException;
	
	/*
	 * this is executed if the command is sent by a player
	 */
	protected abstract void onPlayerExec(ICommandSender sender, String[] args) throws CommandException;
	
	/*
	 * always executed
	 */
	protected abstract void onExec(ICommandSender sender, String[] args) throws CommandException;

}
