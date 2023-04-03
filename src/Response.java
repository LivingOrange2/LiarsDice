
public class Response 
{
	private boolean willCall;
	private String response;
	
	public Response(boolean willCall, String response) 
	{
		this.willCall = willCall;
		this.response = response;
	}

	public boolean willCall() {
		return willCall;
	}

	public void setWillCall(boolean willCall) {
		this.willCall = willCall;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
	
	
}
