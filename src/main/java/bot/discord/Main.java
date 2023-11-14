package bot.discord;

import bot.discord.commands.AdvancedStats;
import bot.discord.commands.PlayerStats;
import bot.discord.commands.Info;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault(Token.botToken).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
        jda.addEventListener(new Listeners());
        jda.addEventListener(new Info());
        try
        {
            Connection conn = DatabaseConnection.getConnection();
            jda.addEventListener(new PlayerStats(conn));
            jda.addEventListener(new AdvancedStats(conn));
        }
        catch (SQLException e)
        {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}
