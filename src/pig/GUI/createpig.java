package pig.GUI;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pig.Main;

public class createpig implements CommandExecutor {

    private Main plugin;

    public createpig(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("createpig").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (p.hasPermission("piggui.zachcreatemob")) {
            if (cmd.getName().equalsIgnoreCase("createpig")) {
                p.chat("/summon minecraft:zombie_pigman ~ ~1 ~ {NoAI:1b}");
            }
        }else {
            p.sendMessage("You don't have permission");
        }

        return false;

    }

}
