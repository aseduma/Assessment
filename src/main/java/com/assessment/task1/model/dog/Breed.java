/**
 * 
 */
package com.assessment.task1.model.dog;

import java.util.List;
import java.util.Map;

/**
 * @author W5100593
 *
 */
public class Breed {
	private String status;
	private Map<String, List<String>> message;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public Map<String, List<String>> getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(Map<String, List<String>> message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Breed [status=" + status + ", message=" + message + "]";
	}

}
