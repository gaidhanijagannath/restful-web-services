package com.in28minutes.rest.webservices.restfulwebservices;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String details;

	public ErrorDetails(LocalDateTime localDate, String message, String details) {
		super();
		this.timestamp = localDate;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
