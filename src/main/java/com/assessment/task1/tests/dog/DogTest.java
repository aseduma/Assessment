package com.assessment.task1.tests.dog;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.assessment.configuration.Configuration;
import com.assessment.report.ReportGenerator;
import com.assessment.report.model.Report;
import com.assessment.report.model.Status;
import com.assessment.task1.model.dog.Breed;
import com.assessment.task1.model.dog.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			Assert.assertEquals(report.getExpected(), report.getStatus());
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
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Response> responseEntity = restTemplate.getForEntity(url, Response.class);

			// Test equal.
			assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

			// get api body
			Response actualResponse = responseEntity.getBody();

			assertNotNull(actualResponse);

			assertEquals(actualResponse.getStatus(), "success");

			assertNotNull(actualResponse.getMessage());

			List<Breed> breeds = actualResponse.getMessage().getBreeds();
			assertNotNull(breeds);

			// check empty list
			assertFalse(breeds.isEmpty());
			report = new Report(Status.PASS, url, new ObjectMapper().convertValue(actualResponse, Map.class));
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
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Response> responseEntity = restTemplate.getForEntity(url, Response.class);

			// Test equal.
			assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

			// get api body
			Response actualResponse = responseEntity.getBody();

			assertNotNull(actualResponse);

			assertEquals(actualResponse.getStatus(), "success");

			assertNotNull(actualResponse.getMessage());

			List<Breed> breeds = actualResponse.getMessage().getBreeds();
			assertNotNull(breeds);

			// check empty list
			assertFalse(breeds.isEmpty());

			List<String> _breeds = new ArrayList<>();

			for (Breed breed : breeds) {
				_breeds.add(breed.getName());
			}
			assertThat(_breeds, hasItem(SUB_BREED));
			report = new Report(Status.PASS, url, new ObjectMapper().convertValue(actualResponse, Map.class));
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
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Response> responseEntity = restTemplate.getForEntity(url, Response.class);

			// Test equal.
			assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

			// get api body
			Response actualResponse = responseEntity.getBody();

			assertNotNull(actualResponse);

			assertEquals(actualResponse.getStatus(), "success");

			assertNotNull(actualResponse.getMessage());

			List<Breed> breeds = actualResponse.getMessage().getBreeds();
			assertNotNull(breeds);

			// check empty list
			assertFalse(breeds.isEmpty());

			List<Breed> subBreeds = new ArrayList<>();
			for (Breed breed : breeds) {
				subBreeds.addAll(breed.getSubBreeds());
			}

			assertNotNull(subBreeds);

			// check empty list
			assertFalse(subBreeds.isEmpty());
			report = new Report(Status.PASS, url, new ObjectMapper().convertValue(actualResponse, Map.class));
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
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Response> responseEntity = restTemplate.getForEntity(url, Response.class);

			// Test equal.
			assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

			// get api body
			Response actualResponse = responseEntity.getBody();

			assertNotNull(actualResponse);

			assertEquals(actualResponse.getStatus(), "success");
			assertNotNull(actualResponse.getMessage());

			assertFalse(actualResponse.getMessage().toString().isEmpty());
			report = new Report(Status.PASS, url, new ObjectMapper().convertValue(actualResponse, Map.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			report = new Report(Status.FAIL, url + e.getMessage());
		}
	}

}
