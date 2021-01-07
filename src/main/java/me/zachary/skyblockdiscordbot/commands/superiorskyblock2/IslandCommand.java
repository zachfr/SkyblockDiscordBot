package me.zachary.skyblockdiscordbot.commands.superiorskyblock2;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.ProgramCommand;
import me.zachary.skyblockdiscordbot.SkyblockDiscordBot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.List;

public class IslandCommand extends ProgramCommand {
    private Bot bot;
    private SkyblockDiscordBot skyblockDiscordBot;

    public IslandCommand(Bot bot, SkyblockDiscordBot skyblockDiscordBot) {
        this.bot = bot;
        this.skyblockDiscordBot = skyblockDiscordBot;
    }
    @Override
    public String getLabel() {
        return skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Command");
    }

    @Override
    protected boolean run(User user, MessageChannel messageChannel, Guild guild, String s, List<String> list) {
        if(list.size() == 0){
            messageChannel.sendMessage(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Invalid usage").replace("%BotPrefix%", bot.getPrefix())).complete();
            return false;
        }
        String playerislandname = list.get(0);
        try {
            SuperiorPlayer island = SuperiorSkyblockAPI.getPlayer(playerislandname);
            if(island == null){
                messageChannel.sendMessage(skyblockDiscordBot.getConfig().getString("No island found")).complete();
                return false;
            }
            String size = String.valueOf(island.getIsland().getIslandSize());
            String structure = String.valueOf(island.getIsland().getSchematicName());
            String level = String.valueOf(island.getIsland().getIslandLevel());
            Float bankbalance = Float.valueOf(String.valueOf(island.getIsland().getIslandBank().getBalance()));
            String bordercolor = island.getBorderColor().name();
            String member = String.valueOf(island.getIsland().getIslandMembers(true).size());
            DecimalFormat bankformat = new DecimalFormat();
            bankformat.setMaximumFractionDigits(2);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Embed title").replace("%PlayerName%", playerislandname));
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Size.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Size.value").replace("%Size%", size), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Structure.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Structure.value").replace("%Structure%", structure), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Levels.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Levels.value").replace("%Level%", level), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Bank Balance.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Bank Balance.value").replace("%BankBalance%", bankformat.format(bankbalance)) + "$", true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Border Color.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Border Color.value").replace("%BorderColor%", bordercolor), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Member Count.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Member Count.value").replace("%MemberCount%", member), true);
            messageChannel.sendMessage(eb.build()).complete();

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getDescription() {
        return skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Island Command.Description");
    }

    public String getUsage() {
        return bot.getPrefix() + "island <PlayerName>";
    }
}
