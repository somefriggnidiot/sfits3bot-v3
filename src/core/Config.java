package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

@SuppressWarnings("unused")
public class Config {
	private static Properties prop = new Properties();
	private static InputStream propInput = null;
	private static OutputStream propOutput = null;
	private static String configFilePath = ""; //TODO load from BotUi.
	
	/*
	 *  Standard Config Commands
	 */
	public static void loadConfig() {
		try {
			propInput = new FileInputStream("bot.cfg");
			prop.load(propInput);
		} catch (IOException iox) {
			System.out.println("An error was encountered while loading the configuration.");
		} finally {
			if (propInput != null) {
				try {
					propInput.close();
				} catch (IOException iox2) {
					iox2.printStackTrace();
				}
			}
		}
	}
	
	public static void saveConfig() {
		try {
			propOutput = new FileOutputStream("bot.cfg");
			prop.store(propOutput, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConfig() {
		try {
			propInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadConfigFile() {
		//TODO File searcher.
	}
	
	public static void saveConfigFile() {
		//TODO Save over currently selected file. Open searcher if no current.
	}
	
	public static void saveConfigFileAs() {
		//TODO File searcher.
	}
	
	enum ConfigProperty {
		Property0(0), Property1(1), Property2(2), Property3(3);
		
		private Integer value;			
		ConfigProperty(Integer value) {
			this.value = value;
		}
		
	}
}
