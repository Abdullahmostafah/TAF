package com.abdullah.dataReader;

import com.abdullah.utils.logs.LogsManager;

import java.io.FileReader;

public class JsonReader {
    private final String TEST_DATA_PATH = "src/test/resources/test-data";
    String jsonReader;
    String jsonFileName;

    public JsonReader(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName));
            jsonReader = data.toJSONString();

        } catch (Exception e) {
            LogsManager.error("Error reading JSON file ", jsonFileName, e.getMessage());
            jsonReader = "{}"; // Fallback to empty JSON object
        }
    }

    public String getJsonData(String jsonPath) {
        try {
            return JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsManager.error("Error reading JSON data from path: ", jsonPath, e.getMessage());
            return ""; // Return null if the path is invalid or an error occurs
        }
    }

}