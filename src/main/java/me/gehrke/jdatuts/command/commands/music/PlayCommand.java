package me.gehrke.jdatuts.command.commands.music;

import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import me.gehrke.jdatuts.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand implements ICommand {
    @SuppressWarnings("ConstantConditions")
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final VoiceChannel memberChannel = memberVoiceState.getChannel();

        if(!audioManager.isConnected()) {
            channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();
        }
        audioManager.openAudioConnection(memberChannel);
        audioManager.setSelfDeafened(true);

        if(ctx.getArgs().isEmpty()){
            channel.sendMessage("Correct usage is `" + Config.get("prefix") + "play <song link>`");
            return;
        }
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        String link = String.join("", ctx.getArgs());
        String front = link.substring(0,link.length() / 2);
        String back = link.substring(link.length() / 2-1, link.length());

        if(!isUrl(link)){
            link = "ytsearch:" + front + " " +back;
        }else{
            link = "ytsearch:" + link;
        }

        PlayerManager.getInstance()
                .loadAndPlay(channel, link);
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "Plays a song\n" +
                "Usage: `" + Config.get("prefix") + "play <song link>`";
    }

    private boolean isUrl(String url){
        try{
            new URI(url);
            return true;
        }catch(URISyntaxException e){
            return false;
        }
    }

}
