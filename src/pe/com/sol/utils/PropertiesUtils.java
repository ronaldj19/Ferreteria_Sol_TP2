
package pe.com.sol.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Eduardo Zuñiga
 *
 */
public class PropertiesUtils {
	
	private static Logger logger = Logger.getLogger(PropertiesUtils.class);
	
	/*
	private static final String BUNDLE_NAME = "app"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);
	*/
	private static Properties properties =  new Properties();
				
	/**
	 * 
	 */
	public PropertiesUtils() {
		InputStream in = this.getClass().getResourceAsStream("/pe/com/sol/utils/app.properties");
		try {
			properties.load(in);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
	}
	/**
	 * @param key
	 * @return
	 */
	/*
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			logger.error(e.getMessage(),e);
		}
		return key;
	}
	*/
	public String getProperty(String key) {
		try {
			return properties.getProperty(key);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return key;
	}
	
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
		
	}
	
}
