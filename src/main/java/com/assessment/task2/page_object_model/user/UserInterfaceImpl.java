/**
 * 
 */
package com.assessment.task2.page_object_model.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.assessment.report.model.Report;
import com.assessment.report.model.Status;
import com.assessment.task1.utils.SeleniumUtil;
import com.assessment.task2.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author w5100593
 *
 */
public class UserInterfaceImpl implements UserInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assessment.task2.page_object_model.user.UserInterface#addUser(java.util.
	 * Map)
	 */
	@Override
	public Report addUser(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<String> screenshots = new ArrayList<String>();

		try {
			SeleniumUtil.clickElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='First Name'])[1]/preceding::button[1]"));
			screenshots.add(SeleniumUtil.takeScreenshot());
			User user = new ObjectMapper().convertValue(map, User.class);
			SeleniumUtil.setElement("FirstName", user.getFirstName());
			SeleniumUtil.setElement("LastName", user.getLastName());
			SeleniumUtil.setElement("UserName", user.getUserName());
			SeleniumUtil.setElement("Password", user.getPassword());
			SeleniumUtil.setElement("optionsRadios", user.getCustomer());
			SeleniumUtil.setElement("RoleId", user.getRole());
			SeleniumUtil.setElement("Email", user.getEmail());
			SeleniumUtil.setElement("Mobilephone", user.getCellPhone());
			screenshots.add(SeleniumUtil.takeScreenshot());
			SeleniumUtil.clickElement(By.xpath("/html/body/div[3]/div[3]/button[2]"));
			screenshots.add(SeleniumUtil.takeScreenshot());

			return new Report(Status.PASS, screenshots, map);
		} catch (Exception e) {
			screenshots.add(SeleniumUtil.takeScreenshot());
			return new Report(Status.FAIL, e.getMessage(), screenshots, map);
		} finally {
			SeleniumUtil.clickElement(By.xpath("/html/body/div[3]/div[3]/button[1]"));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assessment.task2.page_object_model.user.UserInterface#assertUser(java.
	 * util.Map)
	 */
	@Override
	public Report assertUser(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<String> screenshots = new ArrayList<String>();
		try {
			Report report = SeleniumUtil.assertor(By.xpath("/html/body/table"), map);
			screenshots.add(SeleniumUtil.takeScreenshot());
			return report;
		} catch (Exception e) {
			screenshots.add(SeleniumUtil.takeScreenshot());
			return new Report(Status.FAIL, e.getMessage(), screenshots, map);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assessment.task2.page_object_model.user.UserInterface#editUser(java.util.
	 * Map)
	 */
	@Override
	public Report editUser(Map<String, String> map) {
		// TODO Auto-generated method stub
		return new Report(Status.PASS, map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assessment.task2.page_object_model.user.UserInterface#removeUser(java.
	 * util.Map)
	 */
	@Override
	public Report removeUser(Map<String, String> map) {
		// TODO Auto-generated method stub
		return new Report(Status.PASS, map);
	}

}
