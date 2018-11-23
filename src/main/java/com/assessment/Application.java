/**
 * 
 */
package com.assessment;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.assessment.configuration.Configuration;
import com.assessment.task1.tests.dog.DogTest;
import com.assessment.task2.tests.user.UserTest;

/**
 * @author w5100593
 *
 */
public class Application extends Configuration {
	final static Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		try {
			JUnitCore runner = new JUnitCore();
			logger.info("Test start " + new Date());
			Result result = runner.run(DogTest.class, UserTest.class);
			for (Failure failure : result.getFailures()) {
				logger.info(failure.toString());
			}
			logger.info(result.wasSuccessful());
			logger.info("Test end " + new Date());
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
