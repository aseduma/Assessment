/**
 * 
 */
package com.assessment.task1.model.dog;

import java.util.List;

/**
 * @author W5100593
 *
 */
public class SubBreed {
	private String status;
	private List<String> message;

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

	public List<String> getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */

	public void setMessage(List<String> message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubBreed [status=" + status + ", message=" + message + "]";
	}

}
