package net.codingarea.cloudbungeecmds.node;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import de.dytanic.cloudnet.driver.module.ModuleLifeCycle;
import de.dytanic.cloudnet.driver.module.ModuleTask;
import de.dytanic.cloudnet.module.NodeCloudNetModule;
import net.codingarea.cloudbungeecmds.BungeeCommandInfo;
import net.codingarea.cloudbungeecmds.node.listener.BungeeCommandsCloudChannelMessageReceiveListener;

import java.util.Arrays;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public final class CloudNetBungeeCommandsModule extends NodeCloudNetModule {

	private static CloudNetBungeeCommandsModule instance;

	private BungeeCommandsManagement bungeeCommandsManagement;

	@ModuleTask(event = ModuleLifeCycle.LOADED)
	public void onLoad() {
		instance = this;

		bungeeCommandsManagement = new BungeeCommandsManagement();
		getCloudNet().getServicesRegistry().registerService(BungeeCommandsManagement.class, "BungeeCommandsManagement", bungeeCommandsManagement);

		IEventManager eventManager = CloudNetDriver.getInstance().getEventManager();
		eventManager.registerListener(new BungeeCommandsCloudChannelMessageReceiveListener());

		bungeeCommandsManagement.registerCommand(new BungeeCommandInfo("test"), (sender, command, args) -> {
			sender.sendMessage(Arrays.toString(args));
		});
	}

	@ModuleTask(event = ModuleLifeCycle.UNLOADED)
	public void onUnload() {
		bungeeCommandsManagement.unregisterAllCommands();
	}

	public BungeeCommandsManagement getBungeeCommandsManagement() {
		return bungeeCommandsManagement;
	}

	public static CloudNetBungeeCommandsModule getInstance() {
		return instance;
	}

}