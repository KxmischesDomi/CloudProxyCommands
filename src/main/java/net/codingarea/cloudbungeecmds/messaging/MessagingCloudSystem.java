package net.codingarea.cloudbungeecmds.messaging;

public enum MessagingCloudSystem {
		
		CLOUDNET3(
				"de.dytanic.cloudnet.driver.CloudNetDriver",
				"net.codingarea.cloudbungeecmds.messaging.impl.handling.cloudnet3.CloudNet3MessagingHandler"
		),
		SIMPLECLOUD(
				"eu.thesimplecloud.api.eventapi.IEventManager",
				"net.codingarea.cloudbungeecmds.messaging.impl.handling.simplecloud.SimpleCloudMessagingHandler"
		),
		;
		
		private final String testerClassPath;
		private final String handlerClassPath;
		
		MessagingCloudSystem(String testerClassPath, String handlerClassPath) {
			this.testerClassPath = testerClassPath;
			this.handlerClassPath = handlerClassPath;
		}
		
		public boolean isActiveCloud() {
			try {
				Class.forName(testerClassPath);
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		
		public static MessagingHandler getCurrentCloudMessagingHandler() {

			for (MessagingCloudSystem system : values()) {
				try {
					if (system.isActiveCloud()) {
						return (MessagingHandler) Class.forName(system.handlerClassPath).getDeclaredConstructor().newInstance();
					}
				} catch (Exception ignored) {
				}
			}
			return null;
		}
		
	}