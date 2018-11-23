/**
 * 
 */
package com.assessment.model;

import javax.xml.bind.annotation.XmlAttribute;

import com.assessment.report.model.Status;

/**
 * @author w5100593
 *
 */
public class Core {
	private String name;
	private String action;
	private Status expected;

	/**
	 * 
	 */
	public Core() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param action
	 * @param expected
	 */
	public Core(String name, String action, Status expected) {
		super();
		this.name = name;
		this.action = action;
		this.expected = expected;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	@XmlAttribute
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the expected
	 */
	public Status getExpected() {
		return expected;
	}

	/**
	 * @param expected
	 *            the expected to set
	 */
	@XmlAttribute
	public void setExpected(Status expected) {
		this.expected = expected;
	}

}
