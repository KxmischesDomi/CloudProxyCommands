package net.codingarea.cloudbungeecmds.proxy.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.codingarea.cloudbungeecmds.utils.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.proxy.ProxyCommandsPlugin;
import net.codingarea.cloudbungeecmds.proxy.velocity.command.VelocityCloudCommand;

import java.util.logging.Logger;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Plugin(id = "proxy_commands", name = "Proxy-Commands", version = "1.0", url = "coding-area.net", authors = { "KxmischesDomi" })
public class ProxyCommandsVelocityPlugin implements ProxyCommandsPlugin {

	private final ProxyServer server;

	@Inject
	public ProxyCommandsVelocityPlugin(ProxyServer server, Logger logger) {
		this.server = server;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		onPluginLoad();
		onPluginEnable();
	}

	@Subscribe
	public void onProxyEnable(com.velocitypowered.api.event.proxy.ProxyReloadEvent event) {
		onPluginLoad();
		onPluginEnable();
	}

	@Override
	public void registerCommand(ProxyCommandInfo commandInfo) {
		server.getCommandManager().register(commandInfo.getName(),
				new VelocityCloudCommand(commandInfo.getName(), commandInfo.getPermission()),
				commandInfo.getAliases()
		);
	}

	@Override
	public void unregisterCommand(String name) {
		server.getCommandManager().unregister(name);
	}

}