package net.codingarea.cloudbungeecmds.proxy;

import net.codingarea.cloudbungeecmds.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.proxy.management.ProxyMessagingManagement;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ProxyCommandsPlugin {

	AtomicReference<ProxyCommandsPlugin> HOLDER = new AtomicReference<>();

	ProxyMessagingManagement getProxyMessagingManagement();

	void registerCommand(ProxyCommandInfo commandInfo);
	void unregisterCommand(String name);

	static ProxyCommandsPlugin getInstance() {
		return HOLDER.get();
	}

}