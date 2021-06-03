# CloudProxyCommands
 A simple CloudNet Module api to register commands on your proxy and use them
 Looking forward to give support to more cloud systems than only CloudNet

### Installation
 Download the jar (coming soon) and put it into your modules folder and into your proxy plugins.
 Restart the proxy and reload the cloud.

### Getting the manager
 You need to use the command manager to use any feature of this api.
 In all future code examples this variable will be used.

```java
ProxyCommandManagement manager = ProxyCommandsAPI.getCommandsManagement();
```

### Register new command
 The registerCommand methods uses an instance of ProxyCommandInfo which gives all information the proxy needs to register the command, and a ProxyCommandExecutor which has a method execute with a sender, the command and the arguments.  
 You can only give a command name, or you can also give a permission and aliases.
 If the command comes from a player the IProxyCommandSender will be overwritten by ProxyPlayerCommandSender, if the command comes from the console it will be overwritten by ProxyConsoleCommandSendet
 
#### Inline example:

```java
manager.registerCommand(new ProxyCommandInfo("test", "system.testcommand", "t"), (sender, command, args) -> {
		sender.sendMessage(Arrays.toString(args));
});
```

#### Extra class example:

```java
manager.registerCommand(new ProxyCommandInfo("test", "system.testcommand", "t"), new TestCommand());
```

```java
import net.codingarea.cloudbungeecmds.cloud.api.command.ProxyCommandExecutor;

public class TestCommand implements ProxyCommandExecutor {

 @Override
 public void execute(IProxyCommandSender sender, String command, String[] args) {
  sender.sendMessage(Arrays.toString(args));
 }

}
```

### Unregister existing command

To unregister a command from all proxy instances just use this code:

```java
management.unregisterCommand("test");
```