package me.zachary.skyblockdiscordbot;

import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.CommandSpigotManager;
import com.tjplaysnow.discord.object.ThreadSpigot;
import me.zachary.skyblockdiscordbot.commands.*;
import me.zachary.skyblockdiscordbot.commands.fabledskyblock.IslandCommand;
import me.zachary.skyblockdiscordbot.commands.fabledskyblock.UpgradeCommand;
import me.zachary.skyblockdiscordbot.utils.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyblockDiscordBot extends JavaPlugin {
    public Bot bot;
    public final String TOKEN = getConfig().getString("Bot token");
    public final String PREFIX = getConfig().getString("Bot prefix");

    @Override
    public void onEnable() {
        saveDefaultConfig();
        int pluginId = 9534;
        Metrics metrics = new Metrics(this, pluginId);
        // Plugin startup logic
        if(getConfig().getString("Bot token").equalsIgnoreCase("INSERT TOKEN HERE") || getConfig().getString("Bot token") == null){
            Bukkit.getConsoleSender().sendMessage("§4No token set! You can create a bot here: https://discord.com/developers/applications");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        bot = new Bot(TOKEN, PREFIX);
        bot.setBotThread(new ThreadSpigot(this));
        bot.setConsoleCommandManager(new CommandSpigotManager());
        bot.addCommand(new HelpCommand(bot));
        System.out.println("Succesfully load " + bot.getCommands().size() + " commands!");

        if(Bukkit.getPluginManager().getPlugin("FabledSkyBlock") != null) {
            Bukkit.getConsoleSender().sendMessage("§2Successful hook FabledSkyblock!");
            bot.addCommand(new UpgradeCommand(bot));
            //bot.addCommand(new AddBankCommand(bot));
            bot.addCommand(new IslandCommand(bot));
        }
        else if(Bukkit.getPluginManager().getPlugin("ASkyBlock") != null){
            Bukkit.getConsoleSender().sendMessage("§2Successful hook ASkyBlock!");
            bot.addCommand(new me.zachary.skyblockdiscordbot.commands.askyblock.IslandCommand(bot));
        }else if(Bukkit.getPluginManager().getPlugin("SuperiorSkyblock2") != null){
            Bukkit.getConsoleSender().sendMessage("§2Successful hook SuperiorSkyblock2!");
            bot.addCommand(new me.zachary.skyblockdiscordbot.commands.superiorskyblock2.IslandCommand(bot, this));
            bot.addCommand(new me.zachary.skyblockdiscordbot.commands.superiorskyblock2.UpgradeCommand(bot, this));
        }
        else{
            Bukkit.getConsoleSender().sendMessage("§4No skyblock plugin found! Plugin will be disabled");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(bot != null){
            bot.logout();
        }
    }
}
