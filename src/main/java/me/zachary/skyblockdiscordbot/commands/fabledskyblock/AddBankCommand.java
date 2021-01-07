package me.zachary.skyblockdiscordbot.commands.fabledskyblock;

import com.songoda.skyblock.api.SkyBlockAPI;
import com.songoda.skyblock.island.Island;
import com.songoda.skyblock.utils.player.OfflinePlayer;
import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.ProgramCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class AddBankCommand extends ProgramCommand {
    private Bot bot;

    public AddBankCommand(Bot bot) {
        this.bot = bot;
    }
    @Override
    public String getLabel() {
        return "addbank";
    }

    @Override
    protected boolean run(User user, MessageChannel messageChannel, Guild guild, String s, List<String> list) {
        if(list.size() <= 1){
            messageChannel.sendMessage("Invalid usage \n" + bot.getPrefix() + "addbank <PlayerName> <Money>").complete();
            return false;
        }
        String playerislandname = list.get(0);
        try {
            Island island = SkyBlockAPI.getImplementation().getIslandManager().getIsland(new OfflinePlayer(playerislandname).getBukkitOfflinePlayer());
            if(island == null){
                messageChannel.sendMessage("No island found with that name!").complete();
                return false;
            }
            island.addToBank(Double.parseDouble(list.get(1)));
            messageChannel.sendMessage("Successful add " + Double.parseDouble(list.get(1)) + "$ to " + playerislandname + "'s island. He has now " + island.getBankBalance() + "$").complete();

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Add money to bank player.";
    }

    public String getUsage() {
        return bot.getPrefix() + "addbank <PlayerName> <Money>";
    }
}
