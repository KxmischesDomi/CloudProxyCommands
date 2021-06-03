package net.codingarea.cloudbungeecmds.proxy.bungee;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import net.codingarea.cloudbungeecmds.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.proxy.ProxyCommandsPlugin;
import net.codingarea.cloudbungeecmds.proxy.bungee.command.BungeeCommandsCloudNetCommand;
import net.codingarea.cloudbungeecmds.proxy.bungee.listener.BungeeChannelMessageListener;
import net.codingarea.cloudbungeecmds.proxy.management.ProxyMessagingManagement;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BungeeCommandsBungeePlugin extends Plugin implements ProxyCommandsPlugin {

	private ProxyMessagingManagement messagingManagement;

	@Override
	public void onLoad() {
		ProxyCommandsPlugin.HOLDER.set(this);

		messagingManagement = new ProxyMessagingManagement();

		CloudNetDriver.getInstance().getEventManager().registerListener(new BungeeChannelMessageListener());
	}

	@Override
	public void onEnable() {
		messagingManagement.sendOnEnableMessage();
	}

	@Override
	public void unregisterCommand(String name) {
		for (Command command : getCommandsByName(name)) {
			ProxyServer.getInstance().getPluginManager().unregisterCommand(command);
		}
	}

	public Collection<Command> getCommandsByName(String name) {
		return ProxyServer.getInstance().getPluginManager().getCommands()
				.stream().filter(entry -> entry.getKey().equals(name))
				.map(Entry::getValue).collect(Collectors.toList());
	}

	@Override
	public void registerCommand(ProxyCommandInfo commandInfo) {
		getProxy().getPluginManager().registerCommand(this, new BungeeCommandsCloudNetCommand(commandInfo));
	}

	@Override
	public ProxyMessagingManagement getProxyMessagingManagement() {
		return messagingManagement;
	}

}