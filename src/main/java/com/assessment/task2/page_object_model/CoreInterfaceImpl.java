/**
 * 
 */
package com.assessment.task2.page_object_model;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.assessment.report.model.Report;
import com.assessment.task2.config.DriverFactory;
import com.assessment.task2.page_object_model.user.UserInterface;
import com.assessment.task2.page_object_model.user.UserInterfaceImpl;

/**
 * @author w5100593
 *
 */
public class CoreInterfaceImpl implements CoreInterface {

	private UserInterface userInterface;

	/**
	 * 
	 * @return
	 */
	private static WebDriver driver() {
		return DriverFactory.instance().getWebDriver();
	}

	/**
	 * 
	 */
	public CoreInterfaceImpl() {
		if (driver() == null) {
			DriverFactory.instance().init();
		}
		userInterface = new UserInterfaceImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assessment.task2.page_object_model.CoreInterface#test(com.assessment.
	 * task2.model.user.User)
	 */
	@Override
	public Report test(Map<String, String> map) {
		// TODO Auto-generated method stub
		String action = map.get("action");

		switch (action) {
		case "Add User":
			return userInterface.addUser(map);
		case "Assert User":
			return userInterface.assertUser(map);
		case "Edit User":
			return userInterface.editUser(map);
		case "Remove User":
			return userInterface.removeUser(map);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assessment.task2.page_object_model.CoreInterface#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		driver().quit();
	}

}
