package services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ServiceUtil {
	public void AccessDenial(HttpServletResponse response,String message) throws IOException;
	//접근 거부
}
