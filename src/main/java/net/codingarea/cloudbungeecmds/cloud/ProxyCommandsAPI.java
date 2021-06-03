package net.codingarea.cloudbungeecmds.cloud;

import net.codingarea.cloudbungeecmds.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.cloud.api.command.impl.ProxyConsoleCommandSender;
import net.codingarea.cloudbungeecmds.cloud.api.command.impl.ProxyPlayerCommandSender;
import net.codingarea.cloudbungeecmds.management.ProxyCommandManagement;
import org.jetbrains.annotations.NotNull;

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
	}

	default void unloadCommandsModule() {
		MANAGEMENT.get().unregisterAllCommands();
	}

	void sendUnregisterMessage(String commandName);

	void sendRegisterProxyMessage(ProxyCommandInfo commandInfo);

	void registerAllCommandsOnProxy(String proxyName);

	ProxyConsoleCommandSender getConsoleCommandSender();

	ProxyPlayerCommandSender getProxyPlayerCommandSender(@NotNull UUID uuid);

	static ProxyCommandManagement getCommandsManagement() {
		return MANAGEMENT.get();
	}

	static ProxyCommandsAPI getModule() {
		return HOLDER.get();
	}

}