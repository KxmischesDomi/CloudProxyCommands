package net.codingarea.cloudbungeecmds.proxy;

import net.codingarea.cloudbungeecmds.messaging.MessagingHandler;
import net.codingarea.cloudbungeecmds.utils.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.messaging.MessagingReceiver;
import net.codingarea.cloudbungeecmds.messaging.impl.receiving.ProxyMessagingReceiving;
import net.codingarea.cloudbungeecmds.proxy.management.ProxyMessagingManagement;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ProxyCommandsPlugin {

	AtomicReference<ProxyCommandsPlugin> HOLDER = new AtomicReference<>();
	AtomicReference<ProxyMessagingManagement> MANAGEMENT = new AtomicReference<>();

	default void onPluginLoad() {
		HOLDER.set(this);
		MANAGEMENT.set(new ProxyMessagingManagement());
		MessagingHandler.initialize();
		MessagingReceiver.HOLDER.set(new ProxyMessagingReceiving());
	}

	default void onPluginEnable() {
		ProxyCommandsPlugin.getMessagingManagement().sendOnEnableMessage();
	}

	void registerCommand(ProxyCommandInfo commandInfo);
	void unregisterCommand(String name);

	static ProxyMessagingManagement getMessagingManagement() {
		return MANAGEMENT.get();
	}

	static ProxyCommandsPlugin getInstance() {
		return HOLDER.get();
	}

}