package me.gehrke.jdatuts.command.commands;

import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class KickCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Message message = ctx.getMessage();
        final Member member = ctx.getMember();
        final List<String> args = ctx.getArgs();


        if(args.size() < 2 || message.getMentionedUsers().isEmpty()){
            channel.sendMessage("Missing arguments").queue();
            return;
        }

        final Member target = message.getMentionedMembers().get(0);

        if(!member.canInteract(target) || !member.hasPermission(Permission.KICK_MEMBERS)){
            channel.sendMessage("You are missing permission to kick this member").queue();
            return;
        }

        final String reason = String.join(" ", args.subList(1,args.size()));

        ctx.getGuild()
                .kick(target,reason)
                .reason(reason)
                .queue(
                        (success) -> channel.sendMessage("Kick was successful").queue(),
                        (error) -> channel.sendMessageFormat("Could not kick %s", error.getMessage()).queue()
                );
        target.getUser().openPrivateChannel().queue((privateChannel) ->{
            privateChannel.sendMessage("You have been kicked from the discord server reason being: " + reason).queue();
        });
    }

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public String getHelp() {
        return "Kick a member off the server\n" +
                "Usage: `" + Config.get("prefix") +"<@user> <reason>`";
    }
}
