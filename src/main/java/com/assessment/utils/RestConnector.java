/**
 * 
 */
package com.assessment.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

/**
 * @author W5100593
 *
 */
public class RestConnector {
	public static Object getResponseEntityObject(String url, Class clazz) {
		ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(url, String.class);
		// get api body
		return new Gson().fromJson(responseEntity.getBody(), clazz);
	}
}
