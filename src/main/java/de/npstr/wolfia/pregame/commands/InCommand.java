package de.npstr.wolfia.pregame.commands;

import de.npstr.wolfia.Command;
import de.npstr.wolfia.Main;
import de.npstr.wolfia.utils.Player;
import de.npstr.wolfia.pregame.Pregame;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 * Created by npstr on 23.08.2016
 */
public class InCommand extends Command {

    private static final String HELP = "```usage: <prefix>in <minutes>\nwill add you to the signup list for <minutes> (up to 600 mins) and out you automatically afterwards```";
    private final int MAX_SIGNUP_TIME = 10 * 60; //10h

    private final Pregame pg;

    public InCommand(Pregame pg) {
        super();
        this.pg = pg;
    }

    @Override
    public boolean argumentsValid(String[] args, MessageReceivedEvent event) {
        if (args.length < 1)
            return false;
        try {
            Long.valueOf(args[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        //check SING UP counter
        if (Player.getSingups(event.getAuthor().getId()) >= 10) {
            Main.handleOutputMessage(event.getTextChannel(), "Hold on champ. You have reached 100 SING UPs. Submit a " +
                    "karaoke video featuring yourself and ask an admin to reset your SING UP counter.");
            return false;
        }

        long timeForSignup = Long.valueOf(args[0]);
        timeForSignup = timeForSignup < MAX_SIGNUP_TIME ? timeForSignup : MAX_SIGNUP_TIME;
        pg.inPlayer(event.getAuthor().getId(), timeForSignup);

        return true;
    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        if (!success) {
            Main.handleOutputMessage(event.getTextChannel(), Player.asMention(event.getAuthor().getId()) + ":\n" + help());
        }
    }
}
