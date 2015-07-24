
import java.net.URL;

import Utils.Hash;
import Utils.HttpPost;
import Utils.Rijndael;
import DataContracts.ProcessAuthenticationIn;
import DataContracts.ProcessAuthenticationOut;

public class LoginManager {
	private URL m_serviceBaseAddress;
	
	public LoginManager(URL serviceBaseAddress){
		m_serviceBaseAddress = serviceBaseAddress;
	}
	
	public ProcessAuthenticationOut ProcessAuthentication(String encryptedToken, String privateKey, String entryPoint) throws Exception{
		if(encryptedToken == null) throw new IllegalArgumentException("encryptedToken");
		if(privateKey == null) throw new IllegalArgumentException("privateKey");
		String key = Hash.SHA1(privateKey);
		String decryptedToken = Rijndael.Decrypt(encryptedToken, key);
		URL uri = new URL(m_serviceBaseAddress, "/processlogin");
		ProcessAuthenticationIn request = new ProcessAuthenticationIn(decryptedToken, entryPoint);
		ProcessAuthenticationOut response = HttpPost.sendHttpsPost(uri.toString(), request);
		return response;
	}
}
