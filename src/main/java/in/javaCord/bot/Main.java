package in.javaCord.bot;

import in.javaCord.bot.listener.BotListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

public class Main {
    public static void main(String[] args) {
        DiscordApi discordApi = new DiscordApiBuilder().setToken(System.getenv().get("TOKEN")).login().join();
        discordApi.updateActivity(ActivityType.LISTENING, "qb-help");

        discordApi.addListener(new BotListener());

        System.out.println("Bot is ONLINE & RUNNING");
    }
}
