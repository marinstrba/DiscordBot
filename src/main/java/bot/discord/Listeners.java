package bot.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Listeners extends ListenerAdapter {
    /**
     * onReady is a method
     * from class ListenerAdapter,
     * which can be overriden.
     * It tells our bot what to do,
     * when he is connected
     * to the discord server
     */

    @Override
    public void onReady(ReadyEvent event) {
        Guild guild = event.getJDA().getGuildById(ServerID.guildID);
        if(guild != null)
        {
            guild.upsertCommand("info", "Get information about the bot!").queue();
        }
    }
}
