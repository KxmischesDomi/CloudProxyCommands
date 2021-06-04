package net.codingarea.cloudbungeecmds.proxy.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import net.codingarea.cloudbungeecmds.proxy.ProxyCommandsPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class VelocityCloudCommand implements SimpleCommand {

	private final String name;
	private final String permission;

	public VelocityCloudCommand(@NotNull String name, String permission) {
		this.name = name;
		this.permission = permission;
	}

	@Override
	public void execute(Invocation invocation) {
		ProxyCommandsPlugin.getMessagingManagement().executeCommand(name, getUUID(invocation.source()), invocation.arguments());
	}

	@Override
	public boolean hasPermission(Invocation invocation) {
		if (permission == null) return true;
		return invocation.source().hasPermission(permission);
	}

	private UUID getUUID(@NotNull CommandSource commandSender) {
		if (!(commandSender instanceof Player)) return null;
		return ((Player) commandSender).getUniqueId();
	}

}