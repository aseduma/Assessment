/**
 * 
 */
package com.assessment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author w5100593
 *
 */
@XmlRootElement(name = "configuration")
public class Configs {
	private List<Config> config;

	/**
	 * @return the config
	 */
	public List<Config> getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(List<Config> config) {
		this.config = config;
	}

}
