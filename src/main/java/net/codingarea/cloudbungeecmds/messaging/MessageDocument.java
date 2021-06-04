package net.codingarea.cloudbungeecmds.messaging;

import org.jetbrains.annotations.NotNull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface MessageDocument {

	@NotNull
	MessageDocument set(@NotNull String path, Object value);

	String getString(@NotNull String path);

	<T> T get(@NotNull String path, @NotNull Class<T> classOfT);

}