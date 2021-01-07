package me.zachary.skyblockdiscordbot.commands.askyblock;

import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.ProgramCommand;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

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
            OfflinePlayer player = Bukkit.getOfflinePlayer(playerislandname);
            com.wasteofplastic.askyblock.Island island = ASkyBlockAPI.getInstance().getIslandOwnedBy(player.getUniqueId());
            if(island == null){
                messageChannel.sendMessage("No island found with that name!").complete();
                return false;
            }
            String biome = String.valueOf(island.getBiome());
            String hopper = String.valueOf(island.getHopperCount());
            String level = String.valueOf(ASkyBlockAPI.getInstance().getLongIslandLevel(player.getUniqueId()));
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(playerislandname + "'s island stats");
            eb.addField("Biome", biome, true);
            eb.addField("Hopper count", hopper, true);
            eb.addField("Level", level, true);
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
