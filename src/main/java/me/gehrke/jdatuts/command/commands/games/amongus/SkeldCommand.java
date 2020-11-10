package me.gehrke.jdatuts.command.commands.games.amongus;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class SkeldCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();



        final EmbedBuilder embed = EmbedUtils.defaultEmbed()
                .setImage("https://static.wikia.nocookie.net/among-us-wiki/images/7/78/The_Skeld2.jpg/revision/latest/scale-to-width-down/1000?cb=20201026232903");
        channel.sendMessage(embed.build()).queue();

    }

    @Override
    public String getName() {
        return "skeld";
    }

    @Override
    public String getHelp() {
        return "Shows Skeld map\n" +
                "Usage: `" + Config.get("prefix") + "skeld`";
    }
}
