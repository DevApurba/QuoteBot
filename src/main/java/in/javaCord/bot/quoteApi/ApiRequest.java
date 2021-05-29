package in.javaCord.bot.quoteApi;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.Color;
import java.util.Random;

public class ApiRequest {
    String URL = "https://api.quotable.io/random?";
    String data;
    Response response;
    EmbedBuilder builder;

    OkHttpClient okHttpClient = new OkHttpClient();
    Request request ;
    JSONParser jsonParser = new JSONParser();

    public EmbedBuilder random() {
        builder = new EmbedBuilder();

        try {
            request = new Request.Builder().url(URL).build();
            response = okHttpClient.newCall(request).execute();
            data = response.body().string();
            JSONObject randomObject = (JSONObject)jsonParser.parse(data);
            final String AUTHOR = "Author: "+randomObject.get("author");
            final String CONTENT = ""+randomObject.get("content");

            builder.setAuthor(AUTHOR).addField("Random Quotes", CONTENT, true).setColor(randomColor());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }

    public EmbedBuilder famousQuote() {
        builder = new EmbedBuilder();

        try {
            request = new Request.Builder().url(URL+"tags=famous-quotes").build();
            response = okHttpClient.newCall(request).execute();
            data = response.body().string();

            JSONObject famousQuotes = (JSONObject)jsonParser.parse(data);

            final String AUTHOR = "Author: "+famousQuotes.get("author");
            final String CONTENT = ""+famousQuotes.get("content");

            builder.setAuthor(AUTHOR).addField("Famous Quotes",CONTENT).setColor(randomColor());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }

    public EmbedBuilder tagQuote(String TAG) {
        builder = new EmbedBuilder();

        try {
            request = new Request.Builder().url(URL+"tags="+TAG).build();
            response = okHttpClient.newCall(request).execute();
            data = response.body().string();

            JSONObject famousQuotes = (JSONObject)jsonParser.parse(data);

            final String AUTHOR = "Author: "+famousQuotes.get("author");
            final String CONTENT = ""+famousQuotes.get("content");

            if (CONTENT.equalsIgnoreCase("null")) {
                builder.setAuthor("404 Error").addField(":x: Error","Could not find any matching quotes :slight_frown:").setColor(Color.RED);
            }
            else {
                builder.setAuthor(AUTHOR).addField(TAG.toUpperCase()+" Quotes",CONTENT).setColor(randomColor());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }

    public EmbedBuilder tagsQuote(String TAG1, String TAG2) {
        builder = new EmbedBuilder();

        try {
            request = new Request.Builder().url(URL+"tags="+TAG1+"|"+TAG2).build();
            response = okHttpClient.newCall(request).execute();
            data = response.body().string();

            JSONObject famousQuotes = (JSONObject)jsonParser.parse(data);

            final String AUTHOR = "Author: "+famousQuotes.get("author");
            final String CONTENT = ""+famousQuotes.get("content");

            if (CONTENT.equalsIgnoreCase("null")) {
                builder.setAuthor("404 Error").addField(":x: Error","Could not find any matching quotes :slight_frown:").setColor(Color.RED);
            }
            else {
                builder.setAuthor(AUTHOR).addField(TAG1.toUpperCase()+" & "+TAG2.toUpperCase()+" Quotes",CONTENT).setColor(randomColor());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }

    public EmbedBuilder qod() {
        builder = new EmbedBuilder();

        try {
            request = new Request.Builder().url("https://zenquotes.io/api/today").build();
            response = okHttpClient.newCall(request).execute();
            data = response.body().string();

            JSONArray main = (JSONArray)jsonParser.parse(data);

            JSONObject jsonObject = (JSONObject)main.get(0);
            final String QOD = (String)jsonObject.get("q");
            final String AUTHOR = (String)jsonObject.get("a");

            builder.setAuthor(AUTHOR).addField("Quote Of the Day",QOD).setColor(randomColor());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return builder;
    }

    public static Color randomColor() {
        Random random = new Random();

        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r, g, b);
    }
}