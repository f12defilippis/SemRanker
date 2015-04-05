package com.flol.semrankerengine.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.GeographicalTargeting;
import com.flol.semrankercommon.domain.SearchengineParameter;
import com.flol.semrankercommon.repository.GeographicalTargetingRepository;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankerengine.dto.GeographicalCsvTO;
import com.flol.semrankerengine.service.CsvService;
import com.flol.semrankerengine.util.LocalRankUtil;

@RestController
public class GeographicalTargetingLoadingController {

    private final Logger log = LoggerFactory.getLogger(this.getClass()); 
    
	@Autowired
	private SearchengineParameterRepository searchengineParameterRepository;
	
	@Autowired
	private GeographicalTargetingRepository geographicalTargetingRepository;
	
	@Autowired
	private CsvService csvService;
	
	@RequestMapping("/load-geographical-targeting")
	public String loadGeographicalTargeting()
	{
		//get the url of csv file
		SearchengineParameter geographicalTargetingCsvUrlParameter = searchengineParameterRepository.findOne("GEOGRAPHICAL_TARGETING_CSV_URL");
		String geographicalTargetingCsvUrl = geographicalTargetingCsvUrlParameter.getValue();

		// parse csv file in object 
		List<GeographicalCsvTO> placeList = csvService.parseGeographicalCsv(geographicalTargetingCsvUrl);
		// remove header
		placeList.remove(0);
		log.info("Found " + placeList.size() + " Places in CSV File");
		
		// get the prefix of uule
		SearchengineParameter geographicalTargetingUulePrefixParameter = searchengineParameterRepository.findOne("GEOGRAPHICAL_TARGETING_UULE_PR");		
		String geographicalTargetingUulePrefix = geographicalTargetingUulePrefixParameter.getValue();

		int saved = 0, skipped = 0;
		
		for(GeographicalCsvTO place : placeList)
		{
			GeographicalTargeting geoTargeting = geographicalTargetingRepository.findOne(Integer.valueOf(place.getCriteriaId()));
			if(geoTargeting==null)
			{
				geoTargeting = LocalRankUtil.buildGeographicalTargeting(place, geographicalTargetingUulePrefix);
				geographicalTargetingRepository.save(geoTargeting);
				log.info("Saved: Id = " + place.getCriteriaId() + " Canonical Name = " + place.getCanonicalName());
				saved++;
			}else
			{
				log.info("Skipped: Id = " + place.getCriteriaId() + " Canonical Name = " + place.getCanonicalName());
				skipped++;
			}
		}
		String returnMessage = "Record Saved : " + saved + " Record Skipped : " + skipped;
		log.info(returnMessage);
		return returnMessage;
	}
	
	
}
