package bot.discord.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Info extends ListenerAdapter {

    private static final String infoMessage = "\"SoccerStatsBot \uD83C\uDF0D\n" +
            "\n" +
            "Hello football fans! \uD83D\uDC4B Ready to dive into the world of football statistics? I'm here to bring you detailed player stats directly from SoFIFA.com. Whether you're debating who's the best midfielder or planning your fantasy football team, I've got you covered.\n" +
            "\n" +
            "\uD83D\uDD0D What to Expect:\n" +
            "\n" +
            "    Get real-time stats on players from leagues around the world.\n" +
            "    Access detailed information including overall rating, potential, position, club, and more.\n" +
            "    Perfect for in-depth analysis and football discussions in your Discord community.\n" +
            "\n" +
            "\uD83D\uDCCA Why SoFIFA.com Stats?\n" +
            "\n" +
            "    SoFIFA.com is known for its comprehensive and up-to-date football player data.\n" +
            "    It helps you stay informed about player performances and potential.\n" +
            "    Ideal for fans, fantasy football players, and anyone interested in football stats.\n" +
            "\n" +
            "\uD83D\uDE80 How to Use:\n" +
            "\n" +
            "Just type !stats [Player Name] to get the latest statistics of your favorite footballer. Want to know more about what stats you can access? Simply type !help for a list of available commands and features.\n" +
            "\n" +
            "Remember, knowledge is power, especially in the world of football! Whether you're arguing about the best players or planning your next fantasy pick, accurate stats are your best ally.\n" +
            "\n" +
            "Let's explore the world of football together! ⚽\"";
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("info")) return;
        event.reply(infoMessage).queue();
    }
}
