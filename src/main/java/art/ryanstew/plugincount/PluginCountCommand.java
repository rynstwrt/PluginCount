package art.ryanstew.plugincount;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;


public class PluginCountCommand implements CommandExecutor
{
    public static final String RELOAD_PERMISSION = "plugincount.reload";

    private final PluginCount plugin;


    public PluginCountCommand(PluginCount plugin)
    {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload"))
        {
            if (!sender.hasPermission(RELOAD_PERMISSION))
            {
                plugin.sendFormattedMessage(sender, "&cYou do not have permission to run this command!", false);
                return true;
            }

            plugin.reloadConfig();
            plugin.sendFormattedMessage(sender, "&aSuccessfully reloaded PluginCount's config!", true);
            return true;
        }

        int numPlugins = plugin.getServer().getPluginManager().getPlugins().length;

        String configMessage = plugin.getConfig().getString("message");
        if (configMessage == null)
        {
            plugin.sendFormattedMessage(sender, "&cThe \"message\" field was not found in the config!", true);
            return true;
        }

        String message = String.format(configMessage, numPlugins);
        plugin.sendFormattedMessage(sender, message, true);
        return true;
    }
}
