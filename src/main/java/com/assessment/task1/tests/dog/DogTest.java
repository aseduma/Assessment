package com.assessment.task1.tests.dog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.collection.IsMapContaining;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import com.assessment.configuration.Configuration;
import com.assessment.report.ReportGenerator;
import com.assessment.report.model.Report;
import com.assessment.report.model.Status;
import com.assessment.task1.model.dog.Breed;
import com.assessment.task1.model.dog.BreedRandomImage;
import com.assessment.task1.model.dog.SubBreed;
import com.assessment.utils.RestConnector;

/**
 * @author Azael
 *
 */
public class DogTest extends Configuration {
	final static Logger logger = Logger.getLogger(DogTest.class);

	private final String API_URL = "https://dog.ceo/api/";
	private final String BREED = "retriever";
	private final String SUB_BREED = "retriever-golden";

	private static List<Report> reports;

	@BeforeClass
	public static void startUp() {
		reports = new ArrayList<>();
	}

	@AfterClass
	public static void cleanUp() {
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
			Assert.assertEquals(report.getExpected() != null ? report.getExpected() : Status.PASS, report.getStatus());
		} else {
			Assert.assertEquals(Status.PASS, Status.FAIL);
		}
	}

	@Test
	public void listAllBreeds() {
		String url = API_URL + "breeds/list/all";

		logger.info(url);

		try {
			// consume api
			Breed breed = (Breed) RestConnector.getResponseEntityObject(url, Breed.class);

			assertNotNull(breed);

			assertEquals(breed.getStatus(), "success");

			assertNotNull(breed.getMessage());
			// check empty list
			assertFalse(breed.getMessage().isEmpty());
			report = new Report(Status.PASS, url, breed.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			report = new Report(Status.FAIL, url + e.getMessage());
		}

	}

	@Test
	public void verifyBreed() {
		String url = API_URL + "breeds/list/all";

		logger.info(url);
		try {
			// consume api
			Breed breed = (Breed) RestConnector.getResponseEntityObject(url, Breed.class);

			assertNotNull(breed);

			assertEquals(breed.getStatus(), "success");

			assertNotNull(breed.getMessage());
			// check empty list
			assertFalse(breed.getMessage().isEmpty());

			assertThat(breed.getMessage(), IsMapContaining.hasKey(BREED));
			report = new Report(Status.PASS, url, breed.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			report = new Report(Status.FAIL, url + e.getMessage());
		}
	}

	@Test
	public void listSubBreeds() {
		String url = String.format(API_URL + "breed/%s/list", BREED);
		logger.info(url);
		try {
			// consume api
			SubBreed subBreed = (SubBreed) RestConnector.getResponseEntityObject(url, SubBreed.class);

			assertNotNull(subBreed);

			assertEquals(subBreed.getStatus(), "success");

			assertNotNull(subBreed.getMessage());
			// check empty list
			assertFalse(subBreed.getMessage().isEmpty());

			report = new Report(Status.PASS, url, subBreed.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			report = new Report(Status.FAIL, url + e.getMessage());
		}
	}

	@Test
	public void produceRandomImageOrLinkForSubBreed() {
		String url = String.format(API_URL + "breed/%s/images/random", SUB_BREED);
		logger.info(url);
		try {
			// consume api
			BreedRandomImage breedRandomImage = (BreedRandomImage) RestConnector.getResponseEntityObject(url,
					BreedRandomImage.class);

			assertNotNull(breedRandomImage);

			assertEquals(breedRandomImage.getStatus(), "success");
			assertNotNull(breedRandomImage.getMessage());

			assertFalse(breedRandomImage.getMessage().isEmpty());
			report = new Report(Status.PASS, url, breedRandomImage.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			report = new Report(Status.FAIL, url + e.getMessage());
		}
	}

}
