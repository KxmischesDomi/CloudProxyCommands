package net.codingarea.cloudbungeecmds.messaging.impl.handling.simplecloud;

import com.google.gson.JsonObject;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.external.ICloudModule;
import eu.thesimplecloud.api.message.IMessageChannel;
import eu.thesimplecloud.api.message.IMessageListener;
import eu.thesimplecloud.api.network.component.INetworkComponent;
import eu.thesimplecloud.api.service.ICloudService;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.messaging.MessageDocument;
import net.codingarea.cloudbungeecmds.messaging.MessagingHandler;
import net.codingarea.cloudbungeecmds.messaging.MessagingReceiver;
import net.codingarea.cloudbungeecmds.module.ProxyCommandsAPI;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class SimpleCloudMessagingHandler implements MessagingHandler, IMessageListener<SimpleCloudMessageDocument> {

	IMessageChannel<SimpleCloudMessageDocument> messageChannel;

	public SimpleCloudMessagingHandler() {
		try {
			if (!CloudAPI.getInstance().isManager()) {
				registerChannel(CloudAPI.getInstance().getThisSidesCloudModule());
			} else {
				registerChannel(((ICloudModule) ProxyCommandsAPI.getModule()));
			}
		} catch (Exception ex) {
			messageChannel = CloudAPI.getInstance().getMessageChannelManager().getMessageChannelByName(ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME);
		}
		messageChannel.registerListener(this);
	}

	private void registerChannel(ICloudModule cloudModule) {
		messageChannel = CloudAPI.getInstance().getMessageChannelManager().registerMessageChannel(
					cloudModule,
					ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
					SimpleCloudMessageDocument.class
		);
	}

	@Override
	public void sendMessage(String channel, String message, MessageDocument data) {
		SimpleCloudMessageDocument document = (SimpleCloudMessageDocument) data;
		document.set(ProxyCommandsConstants.BUNGEE_COMMANDS_MESSAGE, message);

		List<INetworkComponent> targets = new ArrayList<>();
		if (CloudAPI.getInstance().isManager()) targets.addAll(CloudAPI.getInstance().getCloudServiceManager().getAllCachedObjects());
		else targets.add(INetworkComponent.getMANAGER_COMPONENT());

		messageChannel.sendMessage(document, targets);
	}

	@Override
	public void sendMessageToTask(String channel, String message, MessageDocument data, String taskName) {
		SimpleCloudMessageDocument document = (SimpleCloudMessageDocument) data;
		document.set(ProxyCommandsConstants.BUNGEE_COMMANDS_MESSAGE, message);
		messageChannel.sendMessage(document, CloudAPI.getInstance().getCloudServiceManager().getCloudServicesByGroupName(taskName));
	}

	@Override
	public void sendMessageToService(String channel, String message, MessageDocument data, String serviceName) {
		SimpleCloudMessageDocument document = (SimpleCloudMessageDocument) data;
		document.set(ProxyCommandsConstants.BUNGEE_COMMANDS_MESSAGE, message);
		ICloudService service = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName(serviceName);
		messageChannel.sendMessage(document, service);
	}

	@Override
	public String getCurrentServiceName() {
		return CloudAPI.getInstance().getThisSidesName();
	}

	@Override
	public @NotNull MessageDocument createDocument() {
		return new SimpleCloudMessageDocument(new JsonObject());
	}

	@Override
	public void messageReceived(SimpleCloudMessageDocument simpleCloudMessageDocument, @NotNull INetworkComponent iNetworkComponent) {
		MessagingReceiver.getInstance().handleIncomingMessage(ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				simpleCloudMessageDocument.getString(ProxyCommandsConstants.BUNGEE_COMMANDS_MESSAGE),
				simpleCloudMessageDocument
		);
	}

}