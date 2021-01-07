package me.zachary.skyblockdiscordbot.commands;

import com.tjplaysnow.discord.object.Bot;
import com.tjplaysnow.discord.object.ProgramCommand;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.utils.PermissionUtil;

import java.util.List;
import java.util.Objects;

public class HelpCommand extends ProgramCommand {
    private Bot bot;

    public HelpCommand(Bot bot) {
        this.bot = bot;
    }
    @Override
    public String getLabel() {
        return "help";
    }

    @Override
    protected boolean run(User user, MessageChannel messageChannel, Guild guild, String s, List<String> list) {
        StringBuilder helpMessage = new StringBuilder("**__Commands:__\nBot prefix: " + bot.getPrefix() + "**\n");
        for (ProgramCommand command : bot.getCommands()) {
            if (!messageChannel.getType().equals(ChannelType.PRIVATE)) {
                if (PermissionUtil.checkPermission(Objects.requireNonNull(guild.getMember(user)), command.getPermissionNeeded())) {
                    helpMessage.append("> ").append(command.getLabel()).append("\n   - Description: ").append(command.getDescription()).append("\n");
                }
            } else {
                helpMessage = new StringBuilder("Sorry, but you need to run this command on the server.");
            }
        }
        messageChannel.sendMessage(helpMessage).complete();
        return false;
    }

    @Override
    public String getDescription() {
        return "Help command.";
    }

    public String getUsage() {
        return bot.getPrefix() + "help";
    }
}
