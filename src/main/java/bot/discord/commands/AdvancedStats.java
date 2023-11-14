package bot.discord.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.*;

public class AdvancedStats extends ListenerAdapter {

    private final Connection conn;
    private final String command = "!advanced";
    private final String getPlayerStats = "SELECT * FROM fifa_players WHERE full_name LIKE ?";
    public AdvancedStats(Connection conn)
    {
        this.conn = conn;
        System.out.println("Connection to the database was succesfull!");
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String message = event.getMessage().getContentRaw();
        if(!message.startsWith(command)) return;
        String playerName = message.substring(command.length()).trim();

        if (playerName.isEmpty())
        {
            event.getChannel().sendMessage("Please provide a players name").queue();
        }

        try (PreparedStatement statement = conn.prepareStatement(getPlayerStats))
        {
            statement.setString(1, "%" + playerName + "%");
            ResultSet result = statement.executeQuery();
            if(result.next())
            {
                String finishing = result.getString("finishing");
                String dribbling = result.getString("dribbling");
                String sprintSpeed = result.getString("sprint_speed");
                String strength = result.getString("strength");
                String stamina = result.getString("stamina");
                String outputMessage =
                        "Finishing: " + finishing + "\n" +
                        "Dribbling: " + dribbling + "\n" +
                        "Sprint speed: " + sprintSpeed + "\n" +
                        "Strength: " + strength + "\n" +
                        "Stamina: " + stamina + "\n";
                event.getChannel().sendMessage(outputMessage).queue();
            }
            else
            {
                event.getChannel().sendMessage("Player not found.").queue();
            }
        }
        catch (SQLException e)
        {
            event.getChannel().sendMessage("Mistake while retrieving player data: " + e.getMessage()).queue();
        }
    }

}
