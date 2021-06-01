package net.codingarea.cloudbungeecmds.node;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import de.dytanic.cloudnet.driver.module.ModuleLifeCycle;
import de.dytanic.cloudnet.driver.module.ModuleTask;
import de.dytanic.cloudnet.module.NodeCloudNetModule;
import net.codingarea.cloudbungeecmds.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.node.listener.ProxyCommandsCloudChannelMessageReceiveListener;

import java.util.Arrays;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public final class CloudNetProxyCommandsModule extends NodeCloudNetModule {

	private static CloudNetProxyCommandsModule instance;

	private ProxyCommandsManagement proxyCommandsManagement;

	@ModuleTask(event = ModuleLifeCycle.LOADED)
	public void onLoad() {
		instance = this;

		proxyCommandsManagement = new ProxyCommandsManagement();
		getCloudNet().getServicesRegistry().registerService(ProxyCommandsManagement.class, "BungeeCommandsManagement", proxyCommandsManagement);

		IEventManager eventManager = CloudNetDriver.getInstance().getEventManager();
		eventManager.registerListener(new ProxyCommandsCloudChannelMessageReceiveListener());

		proxyCommandsManagement.registerCommand(new ProxyCommandInfo("test"), (sender, command, args) -> {
			sender.sendMessage(Arrays.toString(args));
		});

		proxyCommandsManagement.unregisterCommand("test");
	}

	@ModuleTask(event = ModuleLifeCycle.UNLOADED)
	public void onUnload() {
		proxyCommandsManagement.unregisterAllCommands();
	}

	public ProxyCommandsManagement getBungeeCommandsManagement() {
		return proxyCommandsManagement;
	}

	public static CloudNetProxyCommandsModule getInstance() {
		return instance;
	}

}