package com.aliyaa.assignment.mmt.flightDetails.exceptions;

public class ErrorResponse {
    private String message;

    public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
     this.message = message;
   }

public ErrorResponse(String message) {
	super();
	this.message = message;
}

public ErrorResponse() {
	super();
}
}
