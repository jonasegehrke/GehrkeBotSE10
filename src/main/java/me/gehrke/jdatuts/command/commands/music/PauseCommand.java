package me.gehrke.jdatuts.command.commands.music;

import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import me.gehrke.jdatuts.lavaplayer.GuildMusicManager;
import me.gehrke.jdatuts.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class PauseCommand implements ICommand {
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

        if(musicManager.audioPlayer.isPaused()){
            musicManager.audioPlayer.setPaused(false);
            channel.sendMessage("Unpausing music").queue();
        }else{
            musicManager.audioPlayer.setPaused(true);
            channel.sendMessage("Pausing the musicplayer type `" + Config.get("prefix") + "pause` to continue listening").queue();
        }
    }

    @Override
    public String getName() {
        return "pause";
    }

    @Override
    public String getHelp() {
        return "Pauses the music bot\n" +
                "Usage: `" + Config.get("prefix") + "pause`";
    }
}
