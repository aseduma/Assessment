/**
 * 
 */
package com.assessment.task2.page_object_model.user;

import java.util.Map;

import com.assessment.report.model.Report;

/**
 * @author w5100593
 *
 */
public interface UserInterface {

	/**
	 * @param user
	 * @return
	 */
	Report addUser(Map<String, String> map);

	/**
	 * @param user
	 * @return
	 */
	Report assertUser(Map<String, String> map);

	/**
	 * @param user
	 * @return
	 */
	Report editUser(Map<String, String> map);

	/**
	 * @param user
	 * @return
	 */
	Report removeUser(Map<String, String> map);

}
