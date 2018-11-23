/**
 * 
 */
package com.assessment.model;

import java.io.File;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.junit.Test;

import com.assessment.configuration.Configuration;

/**
 * @author w5100593
 *
 */
public class ConfigTest extends Configuration {
	@Test
	public void generateData() {

		try {
			String xmlFilePath = System.getProperty("data.config");
			File file = new File(xmlFilePath);
			new File(file.getParent()).mkdirs();
			Configs configs = new Configs();
			configs.setConfig(Arrays.asList(new Config[] { new Config("browser", "chrome") }));

			JAXBContext jaxbContext = JAXBContext.newInstance(Configs.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(configs, file);
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
