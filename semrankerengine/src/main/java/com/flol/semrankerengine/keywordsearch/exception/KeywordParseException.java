package com.flol.semrankerengine.keywordsearch.exception;

import org.jsoup.nodes.Document;

public class KeywordParseException extends Exception{

	private static final long serialVersionUID = -3523331507037888846L;

	private Document document;
	
	public KeywordParseException(Exception e, Document pDocument)
	{
		super(e);
		document = pDocument;
	}

	@Override
	public String getMessage() {
		String message = "ERROR IN PARSING KEYWORD: " + super.getMessage();
		message += document.toString();
		return message;
	}
	
	
}
