package DataContracts;

public class ProcessAuthenticationIn {
	public String DecryptedToken;
	public String EntryPoint;
	
	public ProcessAuthenticationIn(String decryptedToken, String entryPoint) {
		DecryptedToken = decryptedToken;
		EntryPoint = entryPoint;
	}
}
