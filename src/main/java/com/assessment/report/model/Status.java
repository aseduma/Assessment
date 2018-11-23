/**
 * 
 */
package com.assessment.report.model;

/**
 * @author w5100593
 *
 */
public enum Status {
	SUCCESS("Success"), PASS("PASS"), PASSED("Passed"), FAILURE("Failure"), FAIL("FAIL"), FAILED("Failed");

	private final String status;

	private Status(String status) {
		this.status = status;
	}

	public boolean equals(String status) {
		return this.status.equals(status);
	}

	@Override
	public String toString() {
		return this.status;
	}
}
