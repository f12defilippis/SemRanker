package com.flol.semrankerengine.service;

import java.util.List;

import com.flol.semrankerengine.dto.GeographicalCsvTO;

public interface CsvService {

	public List<GeographicalCsvTO> parseGeographicalCsv(String url);
}
