package com.flol.semrankerengine.util;

public class ProxyUtil {

	public static void setProxy(String host, String port, String user, String pwd)
	{
		if(host!=null && !host.equals(""))
		{
			System.setProperty("proxySet", "true");
			System.setProperty("https.proxyHost", host);
			System.setProperty("https.proxyPort", port);
		}		
	}

	public static void removeProxy(String host)
	{
		if(host!=null && !host.equals(""))
		{
			System.setProperty("proxySet", "false");
			System.setProperty("https.proxyHost", "");
			System.setProperty("https.proxyPort", "");
		}		
	}

	
}
