package com.flol.semrankerengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.SearchengineParameter;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankerengine.dto.GeographicalCsvTO;
import com.flol.semrankerengine.service.CsvService;

@RestController
public class GeographicalTargetingLoadingController {

	@Autowired
	private SearchengineParameterRepository searchengineParameterRepository;
	
	@Autowired
	private CsvService csvService;
	
	@RequestMapping("/load-geographical-targeting")
	public String loadGeographicalTargeting()
	{
		SearchengineParameter geographicalTargetingCsvUrlParameter = searchengineParameterRepository.findOne("GEOGRAPHICAL_TARGETING_CSV_URL");
		String geographicalTargetingCsvUrl = geographicalTargetingCsvUrlParameter.getValue();

		List<GeographicalCsvTO> lista = csvService.parseGeographicalCsv(geographicalTargetingCsvUrl);
		
		
		return geographicalTargetingCsvUrl;
		
	}
	
	
}
