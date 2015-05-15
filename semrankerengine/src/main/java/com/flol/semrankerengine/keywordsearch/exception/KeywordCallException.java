package com.flol.semrankerengine.keywordsearch.exception;

public class KeywordCallException extends Exception{

	private static final long serialVersionUID = -3767362096136648348L;

	public KeywordCallException(Exception e)
	{
		super(e);
	}

	@Override
	public String getMessage() {
		return "ERROR OF SEARCHENGINE CALL: " + super.getMessage();
	}
	
}
