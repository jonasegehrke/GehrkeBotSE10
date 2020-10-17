package me.gehrke.jdatuts;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Bot {

    private Bot() throws LoginException {
        WebUtils.setUserAgent("Gehrke");
        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                .setColor(0xdde33d)
        );

        JDABuilder.createDefault(
                Config.get("TOKEN"),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_EMOJIS
        )
                .disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE
                ))

                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.playing("type \"" +Config.get("prefix") + "help\""))
                .addEventListeners(new Listener())
                .build();
    }
    public static void main(String[] args) throws LoginException {
        new Bot();
    }
}
