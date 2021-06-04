package net.codingarea.cloudbungeecmds.messaging;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface MessagingHandler {

	AtomicReference<MessagingHandler> HOLDER = new AtomicReference<>();

	void sendMessage(String channel, String message, MessageDocument data);
	void sendMessageToTask(String channel, String message, MessageDocument data, String taskName);
	void sendMessageToService(String channel, String message, MessageDocument data, String serviceName);

	String getCurrentServiceName();

	@NotNull
	MessageDocument createDocument();

	static void initialize() {
		HOLDER.set(MessagingCloudSystem.getCurrentCloudMessagingHandler());
	}

	static MessagingHandler getInstance() {

		return HOLDER.get();
	}

}