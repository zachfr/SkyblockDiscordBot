package me.zachary.skyblockdiscordbot.commands.superiorskyblock2;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.handlers.UpgradesManager;
import com.bgsoftware.superiorskyblock.api.upgrades.Upgrade;
import com.bgsoftware.superiorskyblock.api.upgrades.UpgradeLevel;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.ProgramCommand;
import me.zachary.skyblockdiscordbot.SkyblockDiscordBot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class UpgradeCommand extends ProgramCommand {
    private Bot bot;
    private SkyblockDiscordBot skyblockDiscordBot;

    public UpgradeCommand(Bot bot, SkyblockDiscordBot skyblockDiscordBot) {
        this.bot = bot;
        this.skyblockDiscordBot = skyblockDiscordBot;
    }

    @Override
    public String getLabel() {
        return skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.Command");
    }

    @Override
    protected boolean run(User user, MessageChannel messageChannel, Guild guild, String s, List<String> list) {
        if(list.size() == 0){
            messageChannel.sendMessage(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.Invalid usage").replace("%BotPrefix%", bot.getPrefix())).complete();
            return false;
        }
        String playerislandname = list.get(0);
        try {
            SuperiorPlayer island = SuperiorSkyblockAPI.getPlayer(playerislandname);
            if(island == null){
                messageChannel.sendMessage(skyblockDiscordBot.getConfig().getString("No island found")).complete();
                return false;
            }
            String hopperslimit = String.valueOf(island.getIsland().getUpgradeLevel("hoppers-limit"));
            String hoppermax = String.valueOf(SuperiorSkyblockAPI.getUpgrades().getUpgrade("hoppers-limit").getMaxUpgradeLevel());
            String cropgrowth = String.valueOf(island.getIsland().getUpgradeLevel("crop-growth"));
            String cropmax = String.valueOf(SuperiorSkyblockAPI.getUpgrades().getUpgrade("crop-growth").getMaxUpgradeLevel());
            String spawnerrates = String.valueOf(island.getIsland().getUpgradeLevel("spawner-rates"));
            String spawnermax = String.valueOf(SuperiorSkyblockAPI.getUpgrades().getUpgrade("spawner-rates").getMaxUpgradeLevel());
            String mobdrops = String.valueOf(island.getIsland().getUpgradeLevel("mob-drops"));
            String mobmax = String.valueOf(SuperiorSkyblockAPI.getUpgrades().getUpgrade("mob-drops").getMaxUpgradeLevel());
            String memberslimit = String.valueOf(island.getIsland().getUpgradeLevel("members-limit"));
            String membermax = String.valueOf(SuperiorSkyblockAPI.getUpgrades().getUpgrade("members-limit").getMaxUpgradeLevel());
            String borderlevel = String.valueOf(island.getIsland().getUpgradeLevel("border-size"));
            String bordermax = String.valueOf(SuperiorSkyblockAPI.getUpgrades().getUpgrade("border-size").getMaxUpgradeLevel());
            String generatorrates = String.valueOf(island.getIsland().getUpgradeLevel("generator-rates"));
            String generatormax = String.valueOf(SuperiorSkyblockAPI.getUpgrades().getUpgrade("generator-rates").getMaxUpgradeLevel());
            String minecartslimit = String.valueOf(island.getIsland().getUpgradeLevel("minecarts-limit"));
            String minecartsmax = String.valueOf(SuperiorSkyblockAPI.getUpgrades().getUpgrade("minecarts-limit").getMaxUpgradeLevel());

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.Embed title").replace("%PlayerName%", playerislandname));
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.hopperslimit.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.hopperslimit.value").replace("%HoppersLimit%", hopperslimit).replace("%HoppersLimitMax%", hoppermax), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.cropgrowth.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.cropgrowth.value").replace("%CropGrowth%", cropgrowth).replace("%CropGrowthMax%", cropmax), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.spawnerrates.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.spawnerrates.value").replace("%SpawnerRates%", spawnerrates).replace("%SpawnerRatesMax%", spawnermax), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.mobdrops.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.mobdrops.value").replace("%MobDrops%", mobdrops).replace("%MobDropsMax%", mobmax), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.memberslimit.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.memberslimit.value").replace("%MembersLimit%", memberslimit).replace("%MembersLimitMax%", membermax), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.borderlevel.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.borderlevel.value").replace("%BorderLevel%", borderlevel).replace("%BorderLevelMax%", bordermax), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.generatorrates.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.generatorrates.value").replace("%GeneratorRates%", generatorrates).replace("%GeneratorRatesMax%", generatormax), true);
            eb.addField(skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.minecartslimit.name"), skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.minecartslimit.value").replace("%MinecartsLimit%", minecartslimit).replace("%MinecartsLimitMax%", minecartsmax), true);
            messageChannel.sendMessage(eb.build()).complete();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getDescription() {
        return skyblockDiscordBot.getConfig().getString("SuperiorSkyblock2 Upgrade Command.Description");
    }
}
