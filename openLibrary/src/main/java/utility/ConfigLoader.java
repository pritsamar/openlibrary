package utility;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	
	public static Properties getConfig() {
		Properties properties = new Properties();
		 try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
	            if (input == null) {
	                System.out.println("Sorry, unable to find config.properties");
	            }
	            properties.load(input);
//	            System.out.println("Database URL: " + properties.getProperty("database.url"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return properties;			
	}

}
