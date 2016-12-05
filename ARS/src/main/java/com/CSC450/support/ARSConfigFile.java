package com.CSC450.support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class ARSConfigFile {
	private String filename = "./ARSConfigFile.txt";
	public static String ACTIVE = "activeRatioWeight";
	public static String FOCUS = "focusRatioWeight";
	public static String MIN = "min";
	public static String MAX = "max";
	
	public ARSConfigFile() {}
	public void write(Double activeRatioWeight, Double focusRatioWeight, Double minValue, Double maxValue) throws FileNotFoundException {
		// write all config settings to text file
		JsonObject configFile = Json.createObjectBuilder()
				.add(ACTIVE, activeRatioWeight)
				.add(FOCUS, focusRatioWeight)
				.add(MIN, minValue)
				.add(MAX, maxValue)
				.build();
		OutputStream outputFile = new FileOutputStream(filename);
		JsonWriter writer = Json.createWriter(outputFile);
		writer.writeObject(configFile);
		writer.close();
	}
	
	public Double get(String key) {
		// Returns config value associated with specified key
		try {
			InputStream inputFile = new FileInputStream(filename);
			JsonReader jsonReader = Json.createReader(inputFile);
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			inputFile.close();
			return jsonObject.getJsonNumber(key).doubleValue();
		}
		catch(FileNotFoundException e) {
			return null;
		}
		catch(IOException e) {
			return null;
		}
		catch(JsonException e) {
			return null;
		}
	}

}
