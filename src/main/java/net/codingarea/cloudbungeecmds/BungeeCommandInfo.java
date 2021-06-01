package net.codingarea.cloudbungeecmds;

import java.util.Arrays;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BungeeCommandInfo {

	private final String name, permission;
	private final String[] aliases;

	public BungeeCommandInfo(String name, String... aliases) {
		this(name, null, aliases);
	}

	public BungeeCommandInfo(String name, String permission, String... aliases) {
		this.name = name;
		this.permission = permission;
		this.aliases = aliases;
	}

	public String getName() {
		return name;
	}

	public String getPermission() {
		return permission;
	}

	public String[] getAliases() {
		return aliases;
	}

	@Override
	public String toString() {
		return "BungeeCommandInfo{" +
				"name='" + name + '\'' +
				", permission='" + permission + '\'' +
				", aliases=" + Arrays.toString(aliases) +
				'}';
	}
}