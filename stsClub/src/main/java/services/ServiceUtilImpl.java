/**
 * 
 */
package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author SAMSUNG
 *
 */
public class ServiceUtilImpl implements ServiceUtil {

	/* (non-Javadoc)
	 * @see services.ServiceUtil#AccessDenial(javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@Override
	public void AccessDenial(HttpServletResponse response, String message) throws IOException {
		// TODO Auto-generated method stub
		
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+message+"');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
	}

}
