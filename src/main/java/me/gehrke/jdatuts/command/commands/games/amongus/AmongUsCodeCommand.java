package me.gehrke.jdatuts.command.commands.games.amongus;

import me.gehrke.jdatuts.Config;
import me.gehrke.jdatuts.command.CommandContext;
import me.gehrke.jdatuts.command.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class AmongUsCodeCommand implements ICommand {
    private List<String> amongUsKey;

    public List<String> getAmongUsKey() {
        return amongUsKey;
    }
    public void setAmongUsKey(List<String> amongUsKey) {
        this.amongUsKey = amongUsKey;
    }

    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final List<String> args = ctx.getArgs();

        if(args.isEmpty()){
            channel.sendMessage("Current code: `" + getAmongUsKey() +"`").queue();
            return;
        }

        setAmongUsKey(args);
        channel.sendMessage("Set among us code to: `" + getAmongUsKey() + "`").queue();
    }

    @Override
    public String getName() {
        return "code";
    }

    @Override
    public String getHelp() {
        return "Set a new code or get the current code\n" +
                "Usage: `" + Config.get("prefix") + "code <among us code>`";
    }
}
