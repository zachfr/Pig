package pig.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import pig.Main;

public class MainGUI implements CommandExecutor, Listener {

    private Main plugin;

    public MainGUI(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("pig").setExecutor(this);
        plugin.getCommand("createpig").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;


        if (cmd.getName().equalsIgnoreCase("pig")) {
            Inventory piggui = Bukkit.getServer().createInventory(p, 54, (ChatColor.translateAlternateColorCodes('&', "&6Test &eZach")));

            piggui.setItem(22, getItem(Material.APPLE, (ChatColor.translateAlternateColorCodes('&', "&4Zach c'est le plus beau"))));;

            p.openInventory(piggui);
        }

        if (label.equalsIgnoreCase("createpig")) {
            if (p.hasPermission("piggui.zachcreatemob")) {
                p.chat("/summon PigZombie ~ ~1 ~ {NoAI:1b,Silent:1,Invulnerable:1}");
                p.sendMessage("You create zombie pig");
            }else {
                p.sendMessage("You don't have permission");
            }
        }


        return false;

    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();
        PlayerInventory inventory = p.getInventory();

        if (current == null) return;

        if (p.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Test &eZach"))) {
            e.setCancelled(true);

            switch (e.getSlot()) {
                case 22:
                    p.sendMessage("Add Zach_FR#5278");
                    p.closeInventory();
                    inventory.addItem(new ItemStack(Material.GOLDEN_APPLE));
                    break;

                default: break;
            }
        }
    }

    @EventHandler
    public void onEntityClick (PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if (e.getRightClicked() instanceof PigZombie) {
            p.chat("/pig");
        }
    }


    public ItemStack getItem(Material material, String customName) {
        ItemStack it = new ItemStack(material, 1);
        ItemMeta itM = it.getItemMeta();
        if(customName != null) itM.setDisplayName(customName);
        it.setItemMeta(itM);
        return it;
    }

}
