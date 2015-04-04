package com.flol.semrankerengine.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserAgentMap {

	public static final Map<Integer,String> USER_AGENTS;
    static {
        Map<Integer, String> USER_AGENTS_ = new HashMap<Integer, String>();
        USER_AGENTS_.put(1, "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
        USER_AGENTS_.put(2, "Googlebot/2.1 (+http://www.googlebot.com/bot.html)");
        USER_AGENTS_.put(3, "Googlebot/2.1 (+http://www.google.com/bot.html)");
        USER_AGENTS_.put(4, "Mozilla/5.0 GurujiBot/1.0 (+http://www.guruji.com/en/WebmasterFAQ.html)");
        USER_AGENTS_.put(5, "Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)");
        USER_AGENTS = Collections.unmodifiableMap(USER_AGENTS_);
    }
	
	public static String getRandomAgent()
	{
		return USER_AGENTS.get(NumberUtil.getRandomInteger(1, USER_AGENTS.size()));
	}
	
	
}
