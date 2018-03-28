package project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DbProperties {
	
	public enum Key{
		TEST_URL("test_url"),
		TEST_USER("test_user"),
		TEST_PASS("test_pass"),
		
		URL("url"),
		USER("user"),
		PASS("pass");
		
		private String key;
		
		Key(String key){
			this.key = key;
		}
		
		@Override
		public String toString() {
			return key;
		}
	}
	
	private static Properties properties;
	
	static {
		properties = new Properties();
		try(FileReader reader = new FileReader("db.properties");) {
			properties.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getDbProperty(Key key) {
		String value = properties.getProperty(key.toString());
		return value;
	}

}
