package com.urbantreasurelimited.ecommerce.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.urbantreasurelimited.ecommerce.constantspath.IPathConstants;

public class JsonUtility {

	public String getDataFromJsonFile(String key) throws IOException, Throwable {
		FileReader fr = new FileReader(IPathConstants.jsonFilePath);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);
		JSONObject map = (JSONObject) obj;
		String data = map.get(key).toString();
		return data;

	}

}
