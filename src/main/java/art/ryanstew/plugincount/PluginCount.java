package art.ryanstew.plugincount;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PluginCount extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("plugincount"))
                .setExecutor(new PluginCountCommand(this));
    }


    @Override
    public void onDisable() { }


    public void sendFormattedMessage(CommandSender sender, String message, boolean usePrefix)
    {
        String withOrWithoutPrefix = usePrefix ? getConfig().getString("prefix") + message : message;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', withOrWithoutPrefix));
    }
}
