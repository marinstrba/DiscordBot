package bot.discord.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.*;

public class PlayerStats extends ListenerAdapter {

    private final Connection conn;
    private final String command = "!stats";
    private final String getPlayerStats = "SELECT * FROM fifa_players WHERE full_name = ?";
    public PlayerStats (Connection conn)
    {
        this.conn = conn;
        System.out.println("Connection was sucessful");
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String message = event.getMessage().getContentRaw();
        if(!message.startsWith(command)) return;
        String playerName = message.substring(command.length()).trim();
        if (playerName.isEmpty())
        {
            event.getChannel().sendMessage("Please provide a players name");
            return;
        }

        try (PreparedStatement statement = conn.prepareStatement(getPlayerStats))
        {
            statement.setString(1, playerName);
            ResultSet result = statement.executeQuery();
            if(result.next())
            {
                String playerData = "Player Data here: ";
                event.getChannel().sendMessage(playerData).queue();
            }
            else
            {
                event.getChannel().sendMessage("Player not found.").queue();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Database error: " + e.getMessage());
            event.getChannel().sendMessage("An error has occured while retrieving player data.").queue();
        }
    }
}
