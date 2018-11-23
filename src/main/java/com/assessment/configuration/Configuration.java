/**
 * 
 */
package com.assessment.configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.assessment.model.Config;
import com.assessment.model.Configs;

/**
 * @author w5100593
 *
 */
public class Configuration {
	final static Logger logger = Logger.getLogger(Configuration.class);
	private Properties properties;

	public Configuration() {

		try {
			loadProperties("/application.properties");

			String filePath = System.getProperty("data.config");

			Configs configuration = (Configs) unmarshal(filePath, Configs.class);

			if (configuration != null) {
				List<Config> configs = configuration.getConfig();
				if (configs != null && configs.size() > 0) {
					for (Config config : configs) {
						System.setProperty(config.getConfig().toLowerCase(), config.getValue());
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void loadProperties(String propertiesFilePath) throws IOException {
		properties = System.getProperties();
		properties.load(getClass().getResourceAsStream(propertiesFilePath));
		String os = properties.getProperty("os.name").toLowerCase();
		for (Object key : properties.keySet()) {
			if (key.toString().startsWith("webdriver")) {
				String path = System.getProperty(key.toString());
				if (os.startsWith("win")) {
					path += ".exe";
				}
				try {
					properties.setProperty(key.toString(), getResourceFilePath(path));
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
		}
		System.setProperties(properties);
	}

	public String readProperty(String key) {
		return properties.getProperty(key);
	}

	private Object unmarshal(String filePath, Class clazz) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				return jaxbUnmarshaller.unmarshal(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getResourceFilePath(String path) {
		return getResourceFile(path).getPath();
	}

	private File getResourceFile(String path) {
		File file = null;
		String resource = path;
		URL res = getClass().getResource(resource);
		if (res.toString().startsWith("jar:")) {
			try {
				InputStream input = getClass().getResourceAsStream(resource);
				file = File.createTempFile("tempfile", ".tmp");
				OutputStream out = new FileOutputStream(file);
				int read;
				byte[] bytes = new byte[1024];

				while ((read = input.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.close();
				input.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			// this will probably work in your IDE, but not from a JAR
			file = new File(res.getFile());
		}

		if (file != null && !file.exists()) {
			throw new RuntimeException("Error: File " + file + " not found!");
		}
		file.deleteOnExit();
		return file;
	}
}
