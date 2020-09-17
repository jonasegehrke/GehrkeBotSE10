package me.gehrke.jdatuts.command.commands.music;

import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import me.gehrke.jdatuts.lavaplayer.GuildMusicManager;
import me.gehrke.jdatuts.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class SkipCommand implements ICommand {
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

        if(musicManager.scheduler.getQueue().isEmpty()){
            channel.sendMessage("There is no music in queue").queue();
            return;
        }

        PlayerManager.getInstance().skipTrack(channel);

    }

    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String getHelp() {
        return "test";
    }
}
