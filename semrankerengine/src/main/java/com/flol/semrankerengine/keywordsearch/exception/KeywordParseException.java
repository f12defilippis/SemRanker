package com.flol.semrankerengine.keywordsearch.exception;

import org.jsoup.nodes.Document;

public class KeywordParseException extends Exception{

	private static final long serialVersionUID = -3523331507037888846L;

	private Document document;
	private String url;
	
	public KeywordParseException(Exception e, Document pDocument)
	{
		super(e);
		document = pDocument;
	}

	public KeywordParseException(Exception e, String pUrl)
	{
		super(e);
		url = pUrl;
	}

	@Override
	public String getMessage() {
		String message = "ERROR IN PARSING KEYWORD: " + super.getMessage();
		if(document!=null)
		{
			message += document.toString();
		}else
		{
			message += url;
		}
		return message;
	}
	
	
}
