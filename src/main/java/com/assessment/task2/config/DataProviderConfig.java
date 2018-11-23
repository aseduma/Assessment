/**
 * 
 */
package com.assessment.task2.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.assessment.task2.model.user.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tngtech.java.junit.dataprovider.DataProvider;

/**
 * @author w5100593
 *
 */
public class DataProviderConfig {
	final static Logger logger = Logger.getLogger(DataProviderConfig.class);

	@DataProvider
	public static Object[][] userDataProvider() {
		String filePath = System.getProperty("data.users");
		Users users = (Users) unmarshal(filePath, Users.class);
		return getDataProvider(users.getUser());
	}

	private static Object unmarshal(String filePath, Class clazz) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				return jaxbUnmarshaller.unmarshal(file);
			}
		} catch (JAXBException e) {
			logger.error(e);
		}
		return null;
	}

	@DataProvider
	private static Object[][] getDataProvider(List<?> objects) {
		if (objects != null && objects.size() > 0) {
			List<Map<String, String>> objectMaps = new ArrayList<Map<String, String>>();
			for (Object object : objects) {
				objectMaps.add(new ObjectMapper().convertValue(object, Map.class));
			}
			Object[][] array = new Object[objectMaps.size()][];
			for (int i = 0; i < objectMaps.size(); i++) {
				array[i] = new Object[1];
				array[i][0] = objectMaps.get(i);
			}
			return array;
		}

		Object[][] array = new Object[1][];
		array[0] = new Object[1];
		array[0][0] = null;
		return array;
	}
}
