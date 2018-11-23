/**
 * 
 */
package com.assessment.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.assessment.report.model.Report;
import com.assessment.report.model.ReportMapper;

/**
 * @author w5100593
 *
 */
public class ReportGenerator {
	final static Logger logger = Logger.getLogger(ReportGenerator.class);

	/**
	 * @param class1
	 * @param reports
	 */
	public static void report(List<Report> reports) {
		// TODO Auto-generated method stub
		if (reports != null && reports.size() > 0) {
			try {
				String fileName = "Report_" + new SimpleDateFormat("yyyy MM dd HH mm ss").format(new Date()) + ".xml";

				String reportBaseDir = System.getProperty("dir.report.dir") + "/"
						+ new SimpleDateFormat("yyyyMMdd").format(new Date());
				new File(reportBaseDir).mkdirs();

				String xmlFilePath = reportBaseDir + "/" + fileName;
				File file = new File(xmlFilePath);
				ReportMapper reportMapper = new ReportMapper();
				reportMapper.setReport(reports);
				JAXBContext jaxbContext = JAXBContext.newInstance(ReportMapper.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(reportMapper, file);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}

}
