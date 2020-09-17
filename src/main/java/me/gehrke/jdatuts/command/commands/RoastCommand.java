package me.gehrke.jdatuts.command.commands;

import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import net.dv8tion.jda.api.entities.User;

import java.util.List;
import java.util.Random;

public class RoastCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        String Roast[] = {" i'd slap you, but that'd be animal abuse",
                " you sound better with your moth closed",
                " if you ran like your mouth you'd be in good shape",
                " here's a tissue. You have a little bullshit on your lip",
                " your face makes onions cry",
                " life is full of disappointments and i just added you to the list"};
        Random random = new Random();
        int randomNumber = (random.nextInt(Roast.length));

        if(ctx.getMessage().getMentionedUsers().isEmpty()){
            ctx.getChannel().sendMessage("Please make sure to mention a user!").queue();
        }else{
            User target = ctx.getMessage().getMentionedUsers().get(0);
            ctx.getChannel().sendMessage("<@"+target.getId() + ">" + Roast[randomNumber]).queue();
        }
    }

    @Override
    public String getName() {
        return "roast";
    }

    @Override
    public String getHelp() {
        return "Roast your friends by mentioning them\n" +
                "Usage: `"+ Config.get("prefix") + "roast <@user>`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("diss");
    }
}
