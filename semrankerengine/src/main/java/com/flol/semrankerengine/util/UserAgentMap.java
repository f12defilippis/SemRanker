package com.flol.semrankerengine.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.flol.semrankercommon.util.NumberUtil;

public class UserAgentMap {

	public static final Map<Integer,String> USER_AGENTS;
	public static final Map<Integer,String> MOBILE_USER_AGENTS;
    static {
        Map<Integer, String> USER_AGENTS_ = new HashMap<Integer, String>();
//        USER_AGENTS_.put(1, "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
//        USER_AGENTS_.put(2, "Googlebot/2.1 (+http://www.googlebot.com/bot.html)");
//        USER_AGENTS_.put(3, "Googlebot/2.1 (+http://www.google.com/bot.html)");
//        USER_AGENTS_.put(4, "Mozilla/5.0 GurujiBot/1.0 (+http://www.guruji.com/en/WebmasterFAQ.html)");
//        USER_AGENTS_.put(1, "Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)");
        USER_AGENTS_.put(1, "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko");
        USER_AGENTS_.put(2, "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        USER_AGENTS_.put(3, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36");
        USER_AGENTS_.put(4, "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        USER_AGENTS_.put(5, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        USER_AGENTS_.put(6, "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0");
        USER_AGENTS_.put(7, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10; rv:33.0) Gecko/20100101 Firefox/33.0");
        USER_AGENTS_.put(8, "Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0");
        USER_AGENTS_.put(9, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20130401 Firefox/31.0");
//        USER_AGENTS_.put(10, "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko");
        
        USER_AGENTS = Collections.unmodifiableMap(USER_AGENTS_);

        //TODO add other mobile ua
        Map<Integer, String> MOBILE_USER_AGENTS_ = new HashMap<Integer, String>();
        MOBILE_USER_AGENTS_.put(1, "Opera/9.80 (J2ME/MIDP; Opera Mini/9.80 (S60; SymbOS; Opera Mobi/23.348; U; en) Presto/2.5.25 Version/10.54");
        MOBILE_USER_AGENTS_.put(2, "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
        MOBILE_USER_AGENTS_.put(3, "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3");
        MOBILE_USER_AGENTS_.put(4, "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7");
        MOBILE_USER_AGENTS = Collections.unmodifiableMap(MOBILE_USER_AGENTS_);
    
    }
	
	public static String getRandomAgent()
	{
		return USER_AGENTS.get(NumberUtil.getRandomInteger(1, USER_AGENTS.size()));
	}
	
	public static String getRandomMobileAgent()
	{
		return MOBILE_USER_AGENTS.get(NumberUtil.getRandomInteger(1, MOBILE_USER_AGENTS.size()));
	}
	
}
