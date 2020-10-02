package jp.bootware.authesample.infrastructure.conf;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:35, Pzt
 **/
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, AuthenticationException e)
      throws IOException, ServletException {
    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getLocalizedMessage());
  }
}
