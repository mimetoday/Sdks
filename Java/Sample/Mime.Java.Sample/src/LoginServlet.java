import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DataContracts.ProcessAuthenticationOut;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    out.println("<HTML>");
	    out.println("<HEAD><TITLE>LoginServlet</TITLE></HEAD>");
	    out.println("<BODY>");
	    out.println("<BIG>You must call LoginServlet by POST http method</BIG>");
	    out.println("</BODY></HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginServletOut result = new LoginServletOut();
		result.Sucess = false;
		Gson gSon = new Gson();
		
		URL url = new URL("https://localhost");
		String privateKey = "123456";
		String token = request.getParameter("Token");
		String status = request.getParameter("Status");
		String entryPoint = "Sample";		
		try {
			if(status.equalsIgnoreCase("Accept")){
				LoginManager client = new LoginManager(url);
				ProcessAuthenticationOut authenticationResponse = client.ProcessAuthentication(token, privateKey, entryPoint);
				switch(authenticationResponse.OperationStatus){
					case "Ok": //OK
						Cookie cookie = new Cookie("USER", authenticationResponse.User);
						cookie.setMaxAge(60*60); //1 hour
						response.addCookie(cookie);
						result.Sucess = true;
						result.UserName = authenticationResponse.User;
						break;
					default:
						result.ErrorMessage = authenticationResponse.ErrorMessage;
						break;
				}
			}
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(gSon.toJson(result));
			out.flush();
		} 
		catch (NoSuchAlgorithmException e) {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(gSon.toJson(result));
			out.flush();
		} 
		catch (Exception e) {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(gSon.toJson(result));
			out.flush();
		}
	}

}
