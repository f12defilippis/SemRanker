package com.flol.semrankerengine.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.flol.semrankerengine.dto.GeographicalCsvTO;
import com.flol.semrankerengine.service.CsvService;

@Service("csvService")
public class CsvServiceImpl implements CsvService{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<GeographicalCsvTO> parseGeographicalCsv(String url)
	{
		InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream(url);
		CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(in)));
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(GeographicalCsvTO.class);
		String[] columns = new String[] {"criteriaId", "name", "canonicalName", "parentId", "countryCode", "targetType", "status"}; // the fields to bind do in your JavaBean
		strat.setColumnMapping(columns);

		CsvToBean csv = new CsvToBean();
		List<GeographicalCsvTO> list = csv.parse(strat, reader);
		return list;
	}
	
	
}
