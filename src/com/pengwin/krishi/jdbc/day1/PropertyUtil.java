package com.pengwin.krishi.jdbc.day1;

import java.io.FileInputStream;		
import java.io.IOException;		
import java.util.Properties;		
		
public class PropertyUtil {		
    private static final String PROPERTY_FILE = "src/com/pengwin/krishi/jdbc/day1/settings.properties";		
		
    public static String getProperty(String key) {		
        Properties properties = new Properties();		
        try {		
            properties.load(new FileInputStream(PROPERTY_FILE));		
            return properties.getProperty(key);		
        } catch (IOException e) {		
            e.printStackTrace();		
            return null;		
        }		
    }		
}		

