package net.codingarea.cloudbungeecmds.node;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import de.dytanic.cloudnet.driver.module.ModuleLifeCycle;
import de.dytanic.cloudnet.driver.module.ModuleTask;
import de.dytanic.cloudnet.module.NodeCloudNetModule;
import net.codingarea.cloudbungeecmds.node.listener.CloudChannelMessageReceiveListener;

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
		getCloudNet().getServicesRegistry().registerService(ProxyCommandManagement.class, "ProxyCommandsManagement", proxyCommandManagement);

		IEventManager eventManager = CloudNetDriver.getInstance().getEventManager();
		eventManager.registerListener(new CloudChannelMessageReceiveListener());
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