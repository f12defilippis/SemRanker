package com.flol.semrankerengine.util;

import org.springframework.util.Base64Utils;

import com.flol.semrankercommon.domain.GeographicalTargeting;
import com.flol.semrankerengine.dto.GeographicalCsvTO;

public class LocalRankUtil {

	public static String encodeCanonicalName(String canonicalName)
	{
		String encoded = Base64Utils.encodeToString(canonicalName.getBytes());
		encoded = encoded.replaceAll("=", "");
		return encoded;
	}
	
	public static String getSecretKey(String canonicalName)
	{
		return GeographicalSecretKeyMap.getKey(canonicalName.length());
	}
	
	public static GeographicalTargeting buildGeographicalTargeting(GeographicalCsvTO place, String prefix)
	{
		GeographicalTargeting geoTargeting = new GeographicalTargeting();
		geoTargeting.setId(Integer.valueOf(place.getCriteriaId()));
		geoTargeting.setName(place.getName());
		geoTargeting.setCanonicalName(place.getCanonicalName());
		geoTargeting.setCountryCode(place.getCountryCode());
		if(place.getParentId()!=null && !place.getParentId().equals(""))
		{
			geoTargeting.setParentId(Integer.valueOf(place.getParentId()));
		}
		geoTargeting.setTargetType(place.getTargetType());
		
		String secretKey = getSecretKey(geoTargeting.getCanonicalName());
		String suffix = encodeCanonicalName(geoTargeting.getCanonicalName());
		
		String uule = prefix + secretKey + suffix;
		
		geoTargeting.setUule(uule);
		return geoTargeting;

	}
	
	
}
