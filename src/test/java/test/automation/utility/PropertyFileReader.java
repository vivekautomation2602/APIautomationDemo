package test.automation.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
	private static final String REQUEST_URL = "requestURL";
	private static final String REQUEST_METHOD = "requestMethod";
	private Properties prop;
	private String propertyFileName;

	public PropertyFileReader(String _propertyFileName) {
		propertyFileName = _propertyFileName;
		prop = new Properties();
	}

	public String getRequestUrl() throws IOException {
		return readConfigFile().getProperty(REQUEST_URL);
	}

	public String getRequestMethod() throws IOException {
		return readConfigFile().getProperty(REQUEST_METHOD);
	}

	private Properties readConfigFile() {
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

}
