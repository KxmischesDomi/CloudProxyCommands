package net.codingarea.cloudbungeecmds.proxy.bungee;

import net.codingarea.cloudbungeecmds.utils.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.proxy.ProxyCommandsPlugin;
import net.codingarea.cloudbungeecmds.proxy.bungee.command.BungeeCloudCommand;
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
public class ProxyCommandsBungeePlugin extends Plugin implements ProxyCommandsPlugin {

	@Override
	public void onLoad() {
		onPluginLoad();
	}

	@Override
	public void onEnable() {
		onPluginEnable();
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
		getProxy().getPluginManager().registerCommand(this, new BungeeCloudCommand(commandInfo));
	}

}