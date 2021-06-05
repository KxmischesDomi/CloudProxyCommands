package net.codingarea.cloudbungeecmds.module.simplecloud.api.command.impl;

import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import net.codingarea.cloudbungeecmds.module.api.command.impl.ProxyPlayerCommandSender;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class SimpleCloudProxyPlayerCommandSender implements ProxyPlayerCommandSender {

	private final UUID uuid;
	ICloudPlayer cloudPlayer;

	public SimpleCloudProxyPlayerCommandSender(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public String getName() {
		return getCloudPlayer().getName();
	}

	@Override
	public void sendMessage(String message) {
		System.out.println("send: " + message);
		getCloudPlayer().sendMessage(message);
	}

	@Override
	public boolean hasPermission(String permission) {
		return cloudPlayer.hasPermissionSync(permission);
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	private ICloudPlayer getCloudPlayer() {
		if (cloudPlayer == null) cloudPlayer = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(uuid);
		return cloudPlayer;
	}

}