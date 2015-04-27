package com.flol.semrushcrawler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    @Scheduled(cron="0 47 * * * *")
    public void reportCurrentTime() {
        try {
			System.out.println(dateFormat.format(new Date()) + " " + getNumberOfUsers("http://www.semrush.com"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: " + e.getMessage());
		}
    }
    
    
    private String getNumberOfUsers(String request) throws IOException
    {
    	Document doc = Jsoup
				.connect(request)
				.userAgent(UserAgentMap.getRandomAgent())
				.timeout(60000).execute().parse();
    	
    	Elements elUsers = doc.getElementsByClass("sem-o-stats-users");
    	
    	Element elUser = elUsers.get(0);
    	
    	String text = elUser.text().replaceAll(",", "").replaceAll(" utenti", "");
    	
    	return text;
    }
    
    
    
}
