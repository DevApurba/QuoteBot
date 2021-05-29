package in.javaCord.bot.listener;

import in.javaCord.bot.quoteApi.ApiRequest;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.Color;

public class BotListener implements MessageCreateListener {
    EmbedBuilder embedBuilder = new EmbedBuilder();
    ApiRequest apiRequest = new ApiRequest();

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] args = event.getMessageContent().split("\\s+");

        if(event.getMessageContent().endsWith("lp")) {
            embedBuilder.setTitle("Bot commands").setColor(Color.PINK);
            event.getChannel().sendMessage(embedBuilder);
        }

        else if(event.getMessageContent().equalsIgnoreCase("-random")) {
            event.getChannel().sendMessage(apiRequest.random());
        }

        else if(event.getMessageContent().equalsIgnoreCase("-famous")) {
            event.getChannel().sendMessage(apiRequest.famousQuote());
        }

        else if(args[0].equalsIgnoreCase("-tag")) {
            if (args.length != 2) {
                embedBuilder.setTitle(":red_circle: Specify the TAG for the Quote").setDescription("Command: -tag "+"<quote-type>").setColor(Color.RED);
                event.getChannel().sendMessage(embedBuilder);
            }
            else {
                String TAG_TYPE = args[1];
                event.getChannel().sendMessage(apiRequest.tagQuote(TAG_TYPE));
            }
        }

        else if(args[0].equalsIgnoreCase("-tags")) {
            if (args.length != 3) {
                embedBuilder.setTitle(":red_circle: Specify the TAGS for the Quote").setDescription("Command: -tags "+"<quote-type 1> "+"<quote-type 2>").setColor(Color.RED);
                event.getChannel().sendMessage(embedBuilder);
            }
            else {
                String firstTag = args[1];
                String secondTag = args[2];
                event.getChannel().sendMessage(apiRequest.tagsQuote(firstTag, secondTag));
            }
        }

        else if(event.getMessageContent().equalsIgnoreCase("-qod")) {
            event.getChannel().sendMessage(apiRequest.qod());
            // event.getChannel().sendMessage(embedBuilder.setAuthor("You Cannot call -qod more than 3 times").setColor(Color.ORANGE));
        }
    }
}