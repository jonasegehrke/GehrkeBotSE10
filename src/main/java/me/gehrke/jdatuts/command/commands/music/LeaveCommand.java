package me.gehrke.jdatuts.command.commands.music;

import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        AudioManager audioManager = ctx.getGuild().getAudioManager();


        if(!audioManager.isConnected()){
            channel.sendMessage("I'm not connected to a voice channel").queue();
            return;
        }

        VoiceChannel voiceChannel = audioManager.getConnectedChannel();

        audioManager.closeAudioConnection();
        channel.sendMessage("Disconnected from your channel").queue();

    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getHelp() {
        return "The bot will leave your channel\n" +
                "Usage: `" + Config.get("prefix") + "leave`";
    }
}
