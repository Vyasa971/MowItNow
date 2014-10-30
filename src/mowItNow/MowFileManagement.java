package mowItNow;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class MowFileManagement {
	
	private static List<String> orderedLines = new ArrayList<>();
	private static Path path;
	
	/**
	 * Default constructor
	 */
	public MowFileManagement(){
		super();
	}
	
	/**
	 * Read the properties's files
	 * @return
	 * @throws IOException
	 */
	private static Properties readFileProperties() throws IOException{
	
		Properties fileprops = new Properties();
		fileprops.load(new FileInputStream("properties/fileConfig.properties"));
		
		return fileprops;		
	}
	
	/**
	 * Get the path from properties 's file
	 * @return
	 * @throws IOException
	 */
	private static String getKeyFromPropertiesFile() throws IOException {
		
		String result = "";
		Properties fileprops = readFileProperties();

		for (String key : fileprops.stringPropertyNames())
			result = fileprops.getProperty(key);

		return result;
	}
	
	/**
	 * Get all lines from the file
	 * @param mowFile
	 * @return
	 * @throws IOException
	 */
	public List<String> getLinesFromFile() throws IOException {

		path = Paths.get(getKeyFromPropertiesFile());
		String line = "";

		try (Scanner filescan = new Scanner(path);) {

			while (filescan.hasNextLine()) {
				line = trimAllSpaces(filescan.nextLine());
				orderedLines.add(line);
			}

			return orderedLines;
		}
	}
	
	/**
	 * Trim all spaces in String
	 * @param stringToTrim
	 * @return
	 */
	private static String trimAllSpaces(String stringToTrim){
		
		return stringToTrim.replaceAll("\\p{Z}", "");
	}
		

}
