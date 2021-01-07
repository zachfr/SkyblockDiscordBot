package me.zachary.skyblockdiscordbot.commands.fabledskyblock;

import com.songoda.skyblock.api.SkyBlockAPI;
import com.songoda.skyblock.island.Island;
import com.songoda.skyblock.utils.player.OfflinePlayer;
import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.ProgramCommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class IslandCommand extends ProgramCommand {
    private Bot bot;

    public IslandCommand(Bot bot) {
        this.bot = bot;
    }
    @Override
    public String getLabel() {
        return "island";
    }

    @Override
    protected boolean run(User user, MessageChannel messageChannel, Guild guild, String s, List<String> list) {
        if(list.size() == 0){
            messageChannel.sendMessage("Invalid usage \n" + bot.getPrefix() + "island <PlayerName>").complete();
            return false;
        }
        String playerislandname = list.get(0);
        try {
            Island island = SkyBlockAPI.getImplementation().getIslandManager().getIsland(new OfflinePlayer(playerislandname).getBukkitOfflinePlayer());
            if(island == null){
                messageChannel.sendMessage("No island found with that name!").complete();
                return false;
            }
            String size = String.valueOf(island.getSize());
            String structure = String.valueOf(island.getStructure());
            String level = String.valueOf(island.getLevel().getLevel());
            String bankbalance = island.getBankBalance() + "$";
            String biome = String.valueOf(island.getBiomeName());
            String status = String.valueOf(island.getStatus());
            String bordercolor = String.valueOf(island.getBorderColor());
            String weather = String.valueOf(island.getWeatherName());

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(playerislandname + "'s island stats");
            eb.addField("Size", size + "X" + size, true);
            eb.addField("Structure", structure, true);
            eb.addField("Levels", level, true);
            eb.addField("Bank Balance", bankbalance, true);
            eb.addField("Biome", biome, true);
            eb.addField("Status", status, true);
            eb.addField("Border color", bordercolor, true);
            eb.addField("Weather", weather, true);
            messageChannel.sendMessage(eb.build()).complete();

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Get island information from a command.";
    }

    public String getUsage() {
        return bot.getPrefix() + "island <PlayerName>";
    }
}
