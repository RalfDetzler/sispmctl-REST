package de.detzler.ralf.sispmctl;

public class SispmctlException extends Exception {

	Integer httpStatus;
	String message;

	public SispmctlException(int status, String message) {

		super(message);
		this.httpStatus = status;
		this.message = message;
	}

	public SispmctlException() {
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
