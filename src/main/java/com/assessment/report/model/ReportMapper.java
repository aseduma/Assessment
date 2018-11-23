/**
 * 
 */
package com.assessment.report.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author w5100593
 *
 */
@XmlRootElement(name = "report-overview")
public class ReportMapper {
	private Date reportDate = new Date();
	private List<Report> report;

	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate
	 *            the reportDate to set
	 */
	@XmlAttribute
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the report
	 */
	public List<Report> getReport() {
		return report;
	}

	/**
	 * @param report
	 *            the report to set
	 */
	public void setReport(List<Report> report) {
		this.report = report;
	}

}
