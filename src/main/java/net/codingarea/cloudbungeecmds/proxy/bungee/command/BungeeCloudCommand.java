package net.codingarea.cloudbungeecmds.proxy.bungee.command;

import net.codingarea.cloudbungeecmds.utils.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.proxy.ProxyCommandsPlugin;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BungeeCloudCommand extends Command {

	public BungeeCloudCommand(ProxyCommandInfo commandInfo) {
		super(commandInfo.getName(), commandInfo.getPermission(), commandInfo.getAliases());
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxyCommandsPlugin.getMessagingManagement().executeCommand(getName(), getUUID(sender), args);
	}

	private UUID getUUID(@NotNull CommandSender commandSender) {
		if (!(commandSender instanceof ProxiedPlayer)) return null;
		return ((ProxiedPlayer) commandSender).getUniqueId();
	}

}