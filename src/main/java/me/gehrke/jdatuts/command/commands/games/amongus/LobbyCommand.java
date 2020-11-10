package me.gehrke.jdatuts.command.commands.games.amongus;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class LobbyCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        Role target = ctx.getGuild().getRoleById("756539941110022207");
        channel.sendMessage(target.getAsMention()).queue();
        final EmbedBuilder embed = EmbedUtils.defaultEmbed()
                .setImage("https://cdn.cloudflare.steamstatic.com/steam/apps/945360/header.jpg?t=1598556351")
                .setTitle(target.getName())
                .setDescription("React hvis du er frisk til at game!");

        channel.sendMessage(embed.build()).queue(message -> message.addReaction("au13:766999526556041226").queue());


    }

    @Override
    public String getName() {
        return "lobby";
    }

    @Override
    public String getHelp() {
        return "Start a lobby and see who is up for gaming!\n" +
                "Usage: `" + Config.get("prefix") + "lobby`";
    }
}
