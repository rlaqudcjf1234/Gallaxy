package dto;

public class HttpDTO {

	private int responseCode;
	private String responseText;

	public HttpDTO() {
	}

	public HttpDTO(int responseCode, String responseText) {
		super();
		this.responseCode = responseCode;
		this.responseText = responseText;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseText() {
		return responseText;
	}
}
