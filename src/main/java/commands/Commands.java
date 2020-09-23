package commands;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class Commands {
	
	private static ShareLanCommand sharelan = new ShareLanCommand();
	private static WindowCommand window = new WindowCommand();
	
	
	
	
	@SideOnly(Side.CLIENT)
	public static void CRegister() {
		ClientCommandHandler.instance.registerCommand( window );
	}
	
	public static void SRegister(FMLServerStartingEvent event) {
		event.registerServerCommand( sharelan );
	}
	
}
