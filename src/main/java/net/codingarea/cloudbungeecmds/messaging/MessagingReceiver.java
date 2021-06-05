package net.codingarea.cloudbungeecmds.messaging;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface MessagingReceiver {

	AtomicReference<MessagingReceiver> HOLDER = new AtomicReference<>();

	void handleIncomingMessage(String channel, String message, MessageDocument data);

	static MessagingReceiver getInstance() {
		return HOLDER.get();
	}

}