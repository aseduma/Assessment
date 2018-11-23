/**
 * 
 */
package com.assessment.task2.model.user;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author w5100593
 *
 */
@XmlRootElement(name = "users")
public class Users {
	private List<User> user;

	/**
	 * @return the user
	 */
	public List<User> getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(List<User> user) {
		this.user = user;
	}

}
