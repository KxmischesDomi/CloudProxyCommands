package net.codingarea.cloudbungeecmds.messaging.impl.handling.cloudnet3;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.channel.ChannelMessage;
import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.channel.ChannelMessageReceiveEvent;
import net.codingarea.cloudbungeecmds.messaging.MessageDocument;
import net.codingarea.cloudbungeecmds.messaging.MessagingHandler;
import net.codingarea.cloudbungeecmds.messaging.MessagingReceiver;
import org.jetbrains.annotations.NotNull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CloudNet3MessagingHandler implements MessagingHandler {

	public CloudNet3MessagingHandler() {
		CloudNetDriver.getInstance().getEventManager().registerListener(this);
	}

	@Override
	public void sendMessage(String channel, String message, MessageDocument data) {
		CloudNet3MessageDocument document = (CloudNet3MessageDocument) data;

		ChannelMessage.builder()
				.channel(channel)
				.message(message)
				.json(document.getDocument())
				.build()
				.send();
	}

	@Override
	public void sendMessageToTask(String channel, String message, MessageDocument data, String taskName) {
		CloudNet3MessageDocument document = (CloudNet3MessageDocument) data;

		ChannelMessage.builder()
				.channel(channel)
				.message(message)
				.json(document.getDocument())
				.targetService(taskName)
				.build()
				.send();
	}

	@Override
	public void sendMessageToService(String channel, String message, MessageDocument data, String serviceName) {
		CloudNet3MessageDocument document = (CloudNet3MessageDocument) data;

		ChannelMessage.builder()
				.channel(channel)
				.message(message)
				.json(document.getDocument())
				.targetService(serviceName)
				.build()
				.send();
	}

	@Override
	public String getCurrentServiceName() {
		return CloudNetDriver.getInstance().getComponentName();
	}

	@EventListener
	public void onMessageReceive(ChannelMessageReceiveEvent event) {
		MessagingReceiver.getInstance().handleIncomingMessage(event.getChannel(), event.getMessage(), new CloudNet3MessageDocument(event.getData()));
	}

	@Override
	public @NotNull MessageDocument createDocument() {
		return new CloudNet3MessageDocument(new JsonDocument());
	}

}