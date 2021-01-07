package me.zachary.skyblockdiscordbot.commands.fabledskyblock;

import com.songoda.skyblock.api.SkyBlockAPI;
import com.songoda.skyblock.island.Island;
import com.songoda.skyblock.upgrade.Upgrade;
import com.songoda.skyblock.utils.player.OfflinePlayer;
import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.ProgramCommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class UpgradeCommand extends ProgramCommand {
    private Bot bot;

    public UpgradeCommand(Bot bot) {
        this.bot = bot;
    }
    @Override
    public String getLabel() {
        return "upgrade";
    }

    @Override
    protected boolean run(User user, MessageChannel messageChannel, Guild guild, String s, List<String> list) {
        if(list.size() == 0){
            messageChannel.sendMessage("Invalid usage \n" + bot.getPrefix() + "upgrade <PlayerName>").complete();
            return false;
        }
        String playerislandname = list.get(0);
        Island island = SkyBlockAPI.getImplementation().getIslandManager().getIsland(new OfflinePlayer(playerislandname).getBukkitOfflinePlayer());
        if(island == null){
            messageChannel.sendMessage("No island found with that name!").complete();
            return false;
        }
        try {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(playerislandname + "'s upgrade stats");
            if(island.hasUpgrade(Upgrade.Type.valueOf("Crop")))
                eb.addField("Crop upgrade", "Yes", true);
            else
                eb.addField("Crop upgrade", "No", true);
            if(island.hasUpgrade(Upgrade.Type.valueOf("Spawner")))
                eb.addField("Spawner upgrade", "Yes", true);
            else
                eb.addField("Spawner upgrade", "No", true);
            if(island.hasUpgrade(Upgrade.Type.valueOf("Fly")))
                eb.addField("Fly upgrade", "Yes", true);
            else
                eb.addField("Fly upgrade", "No", true);
            if(island.hasUpgrade(Upgrade.Type.valueOf("Drops")))
                eb.addField("Drops upgrade", "Yes", true);
            else
                eb.addField("Drops upgrade", "No", true);
            if(island.hasUpgrade(Upgrade.Type.valueOf("Size")))
                eb.addField("Size upgrade", "Yes", true);
            else
                eb.addField("Size upgrade", "No", true);
            if(island.hasUpgrade(Upgrade.Type.valueOf("Speed")))
                eb.addField("Speed upgrade", "Yes", true);
            else
                eb.addField("Speed upgrade", "No", true);
            if(island.hasUpgrade(Upgrade.Type.valueOf("Jump")))
                eb.addField("Jump upgrade", "Yes", true);
            else
                eb.addField("Jump upgrade", "No", true);

            messageChannel.sendMessage(eb.build()).complete();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Get upgrade island information by a command.";
    }


    public String getUsage() {
        return bot.getPrefix() + "upgrade <PlayerName>";
    }
}
