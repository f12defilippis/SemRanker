package com.flol.semrankerengine.keywordsearch;

public class KeywordStoreDataException extends Exception{

	private static final long serialVersionUID = 7541539774280522882L;

	public KeywordStoreDataException(Exception e)
	{
		super(e);
	}
	
	@Override
	public String getMessage() {
		return "ERROR IN STORING KEYWORD DATA IN DATABASE: " + super.getMessage();
	}
	
	

}
