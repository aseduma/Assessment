/**
 * 
 */
package com.assessment.task2.tests.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import com.assessment.report.ReportGenerator;
import com.assessment.report.model.Report;
import com.assessment.report.model.Status;
import com.assessment.task2.config.DataProviderConfig;
import com.assessment.task2.page_object_model.CoreInterface;
import com.assessment.task2.page_object_model.CoreInterfaceImpl;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

/**
 * @author Azael
 *
 */
@RunWith(DataProviderRunner.class)
public class UserTest {
	final static Logger logger = Logger.getLogger(UserTest.class);

	private static CoreInterface coreInterface;

	private static List<Report> reports;

	@BeforeClass
	public static void startUp() {
		reports = new ArrayList<>();
		coreInterface = new CoreInterfaceImpl();
	}

	@AfterClass
	public static void tearDown() {
		coreInterface.close();
		ReportGenerator.report(reports);
	}

	private Report report;
	private LocalDate localDate;
	private long start;

	@Rule
	public TestName name = new TestName();

	@Before
	public void initResult() {
		localDate = new LocalDate();
		start = System.nanoTime();
	}

	@After
	public void addResult() {
		if (report != null) {
			report.setMethod(name.getMethodName());
			report.setDate(localDate);
			report.setDuration(System.nanoTime() - start);
			reports.add(report);
			Assert.assertEquals(report.getExpected(), report.getStatus());
		} else {
			Assert.assertEquals(Status.PASS, Status.FAIL);
		}
	}

	@Test
	@UseDataProvider(value = "userDataProvider", location = DataProviderConfig.class)
	public void users(Map<String, String> data) {
		report = coreInterface.test(data);
	}

}
