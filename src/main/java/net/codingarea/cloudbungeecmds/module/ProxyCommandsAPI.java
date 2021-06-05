package net.codingarea.cloudbungeecmds.module;

import net.codingarea.cloudbungeecmds.messaging.MessagingHandler;
import net.codingarea.cloudbungeecmds.messaging.MessagingReceiver;
import net.codingarea.cloudbungeecmds.messaging.impl.receiving.CloudMessagingReceiving;
import net.codingarea.cloudbungeecmds.module.api.command.impl.ProxyConsoleCommandSender;
import net.codingarea.cloudbungeecmds.module.api.command.impl.ProxyPlayerCommandSender;
import net.codingarea.cloudbungeecmds.module.management.ProxyCommandManagement;
import net.codingarea.cloudbungeecmds.utils.ProxyCommandInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ProxyCommandsAPI {

	AtomicReference<ProxyCommandsAPI> HOLDER = new AtomicReference<>();
	AtomicReference<ProxyCommandManagement> MANAGEMENT = new AtomicReference<>();

	default void loadCommandsModule() {
		HOLDER.set(this);
		MANAGEMENT.set(new ProxyCommandManagement());
		MessagingHandler.initialize();
		MessagingReceiver.HOLDER.set(new CloudMessagingReceiving());

		getCommandsManagement().registerCommand(new ProxyCommandInfo("test"), (sender, command, args) -> {
			sender.sendMessage(Arrays.toString(args));
		});
	}

	default void unloadCommandsModule() {
		MANAGEMENT.get().unregisterAllCommands();
	}

	ProxyConsoleCommandSender getConsoleCommandSender();

	ProxyPlayerCommandSender getProxyPlayerCommandSender(@NotNull UUID uuid);

	static ProxyCommandManagement getCommandsManagement() {
		return MANAGEMENT.get();
	}

	static ProxyCommandsAPI getModule() {
		return HOLDER.get();
	}

}