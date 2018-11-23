package com.assessment.task1.model.dog;

import java.util.List;

/**
 * @author Azael
 *
 */
public class Breed {
	private String name;
	private List<Breed> subBreeds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Breed> getSubBreeds() {
		return subBreeds;
	}

	public void setSubBreeds(List<Breed> subBreeds) {
		this.subBreeds = subBreeds;
	}
}
