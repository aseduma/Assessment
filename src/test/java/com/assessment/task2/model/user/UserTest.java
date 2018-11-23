/**
 * 
 */
package com.assessment.task2.model.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.junit.Test;

import com.assessment.configuration.Configuration;
import com.assessment.report.model.Status;

/**
 * @author w5100593
 *
 */
public class UserTest extends Configuration {
	@Test
	public void generateData() {
		int userSize = 20;
		Random random = new Random();
		// Company AAA - 15
		// Company BBB - 16
		String customers[] = new String[] { "15", "16" };
		String roles[] = new String[] { "Sales Team", "Customer", "Admin" };
		Status expectedStatus[] = new Status[] { Status.PASS, Status.FAIL };
		String actions[] = new String[] { "Add User", "Assert User", "Edit User", "Remove User" };

		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < userSize; i++) {
			int randomUser = random.nextInt(1000);
			userList.add(new User("User Suite " + i, actions[random.nextInt(4)], expectedStatus[random.nextInt(2)],
					"Test " + randomUser, "Test " + randomUser, "test" + randomUser, "1234",
					customers[random.nextInt(2)], roles[random.nextInt(3)], "test" + randomUser + "@test.com",
					"0743519649", "false"));
		}

		try {
			String xmlFilePath = System.getProperty("data.users");
			File file = new File(xmlFilePath);
			new File(file.getParent()).mkdirs();

			Users users = new Users();
			users.setUser(userList);
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(users, file);
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
