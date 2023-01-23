package com.catacid.catacidtg;

import com.catacid.catacidtg.generator.Generator;
import com.catacid.catacidtg.oldGenerator.textr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
public class BotInitializer {

    @Autowired
    TelegramBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);


        Generator gen = new Generator();
        String rootPath = System.getProperty("user.dir");
        String contentPath = rootPath + "\\content";
        List<File> content = Arrays.asList(new File(contentPath).listFiles());
        gen.setContent(content);

      //  String answer = gen.genBookAnswer("сука");

        String answer2 = textr.generateOne(5,5);

        System.out.println(answer2);
        //addContent();
    }

    public static void addContent() {
        try {
            //content = Arrays.asList(new File(contentPath).listFiles());
           // conferenceBot.gen.setContent(content );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
