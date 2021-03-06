package org.screamingsandals.simpleinventories.loaders;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.screamingsandals.simpleinventories.dependencies.DependencyHelper;
import org.screamingsandals.simpleinventories.inventory.LocalOptions;
import org.screamingsandals.simpleinventories.inventory.Origin;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class CsvLoader implements Loader {

	@Override
	public Origin readData(File file, String configPath, LocalOptions options) throws Exception {
		DependencyHelper.UNIVOCITY.load();

		List<Object> list = new ArrayList<>();

		CsvParserSettings settings = new CsvParserSettings();
		settings.detectFormatAutomatically();
		
	    CsvParser parser = new CsvParser(settings);
	    List<String[]> rows = parser.parseAll(file);
	    
	    String[] header = null;
	    for (String[] row : rows) {
	    	if (header == null) {
	    		header = row;
	    	} else {
	    		Map<String, Object> map = new HashMap<>();
	    		for (int i = 0; i < row.length && i < header.length; i++) {
	    			map.put(header[i], row[i]);
	    		}
	    	}
	    }
		
		return new Origin(file, list);
	}

}
