/**
 * 
 */
package com.assessment.report.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.LocalDate;

/**
 * @author w5100593
 *
 */
@XmlRootElement
public class Report {
	private String method;
	private Status status;
	private Status expected;
	private String comments;
	private List<String> screenshots;
	private Object data;
	private LocalDate date;
	private long duration;

	/**
	 * 
	 */
	public Report() {
	}

	/**
	 * @param status
	 */
	public Report(Status status) {
		super();
		this.status = status;
	}

	/**
	 * @param status
	 * @param data
	 */
	public Report(Status status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	/**
	 * @param status
	 * @param comments
	 */
	public Report(Status status, String comments) {
		super();
		this.status = status;
		this.comments = comments;
	}

	/**
	 * @param status
	 * @param comments
	 * @param data
	 */
	public Report(Status status, String comments, Object data) {
		super();
		this.status = status;
		this.comments = comments;
		this.data = data;
	}

	/**
	 * @param status
	 * @param comments
	 * @param screenshots
	 * @param data
	 */
	public Report(Status status, String comments, List<String> screenshots, Object data) {
		super();
		this.status = status;
		this.comments = comments;
		this.screenshots = screenshots;
		this.data = data;
	}

	/**
	 * @param status
	 * @param screenshots
	 * @param data
	 */
	public Report(Status status, List<String> screenshots, Object data) {
		super();
		this.status = status;
		this.screenshots = screenshots;
		this.data = data;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	@XmlAttribute
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	@XmlElement
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	@XmlElement
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the screenshots
	 */
	public List<String> getScreenshots() {
		return screenshots;
	}

	/**
	 * @param screenshots
	 *            the screenshots to set
	 */
	@XmlElement
	public void setScreenshots(List<String> screenshots) {
		this.screenshots = screenshots;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	@XmlElement
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	@XmlElement
	public void setDuration(long duration) {
		this.duration = duration;
	}

	public boolean isFail() {
		return status == null || status == Status.FAIL;
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
	@XmlElement
	public void setExpected(Status expected) {
		this.expected = expected;
	}

}
