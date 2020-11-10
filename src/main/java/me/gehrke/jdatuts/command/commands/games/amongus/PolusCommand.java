package me.gehrke.jdatuts.command.commands.games.amongus;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class PolusCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();



        final EmbedBuilder embed = EmbedUtils.defaultEmbed()
                .setImage("https://static.wikia.nocookie.net/among-us-wiki/images/f/fb/PolusMap.png/revision/latest/scale-to-width-down/1000?cb=20200907133344");
        channel.sendMessage(embed.build()).queue();

    }

    @Override
    public String getName() {
        return "polus";
    }

    @Override
    public String getHelp() {
        return "Shows Polus map\n" +
                "Usage: `" + Config.get("prefix") + "polus`";
    }
}
