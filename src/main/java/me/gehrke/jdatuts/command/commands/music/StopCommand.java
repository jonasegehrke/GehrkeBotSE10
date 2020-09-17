package me.gehrke.jdatuts.command.commands.music;

import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import me.gehrke.jdatuts.lavaplayer.GuildMusicManager;
import me.gehrke.jdatuts.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class StopCommand implements ICommand {
    @SuppressWarnings("ConstantConditions")
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getMusicManager(ctx.getGuild());

        if(!selfVoiceState.inVoiceChannel()){
            channel.sendMessage("I am currently not in a voice channel").queue();
            return;
        }

        if(musicManager.audioPlayer.getPlayingTrack() == null){
            channel.sendMessage("I am currently not playing any music").queue();
            return;
        }

        musicManager.scheduler.getQueue().clear();
        musicManager.audioPlayer.stopTrack();
        musicManager.audioPlayer.setPaused(false);

        channel.sendMessage("Stopping the music player and clearing the queue").queue();
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getHelp() {
        return "Stops the ongoing music\n" +
                "Usage: `" + Config.get("prefix") + "stop´";
    }
}
