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

	private ProxyCommandManagement proxyCommandManagement;

	@ModuleTask(event = ModuleLifeCycle.LOADED)
	public void onLoad() {
		instance = this;

		proxyCommandManagement = new ProxyCommandManagement();
		getCloudNet().getServicesRegistry().registerService(ProxyCommandManagement.class, "BungeeCommandsManagement", proxyCommandManagement);

		IEventManager eventManager = CloudNetDriver.getInstance().getEventManager();
		eventManager.registerListener(new ProxyCommandsCloudChannelMessageReceiveListener());

		proxyCommandManagement.registerCommand(new ProxyCommandInfo("test"), (sender, command, args) -> {
			sender.sendMessage(Arrays.toString(args));
		});

		proxyCommandManagement.unregisterCommand("test");
	}

	@ModuleTask(event = ModuleLifeCycle.UNLOADED)
	public void onUnload() {
		proxyCommandManagement.unregisterAllCommands();
	}

	public ProxyCommandManagement getBungeeCommandsManagement() {
		return proxyCommandManagement;
	}

	public static CloudNetProxyCommandsModule getInstance() {
		return instance;
	}

}