/**
 * 
 */
package com.assessment.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author w5100593
 *
 */
@XmlRootElement(name = "config")
public class Config {
	private String config;
	private String value;

	/**
	 * 
	 */
	public Config() {
	}

	/**
	 * @param config
	 * @param value
	 */
	public Config(String config, String value) {
		super();
		this.config = config;
		this.value = value;
	}

	/**
	 * @return the config
	 */
	public String getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	@XmlAttribute
	public void setConfig(String config) {
		this.config = config;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}

}
