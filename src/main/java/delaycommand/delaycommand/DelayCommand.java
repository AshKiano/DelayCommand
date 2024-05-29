package delaycommand.delaycommand;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DelayCommand extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();

        Metrics metrics = new Metrics(this, 21898);
        this.getLogger().info("Thank you for using the DelayCommand plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String command = getConfig().getString("command").replace("%player%", player.getName());
        int delayMinutes = getConfig().getInt("delayMinutes");

        Bukkit.getScheduler().runTaskLater(this, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            getLogger().info("Command has been executed for " + player.getName());
        }, delayMinutes * 20 * 60);
    }
}
