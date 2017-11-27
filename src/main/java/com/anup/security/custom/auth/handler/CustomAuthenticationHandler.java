package com.anup.security.custom.auth.handler;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
/**
 *
 * @author Raichand
 */
/*
 * This Class Redirects to authorised page according to role of the person logging in.
 */
public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {
 
 @Override
 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
Authentication authentication) throws ServletException, IOException {
      String userTargetUrl = "/user/welcome.jsf";
      String adminTargetUrl = "/admin/Admin.jsf";
      String zebraTargetUrl = "/zebra/welcome.jsf";
      Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
      if (roles.contains("ROLE_ADMIN")) {
         getRedirectStrategy().sendRedirect(request, response, adminTargetUrl);
      } else if (roles.contains("ROLE_USER")) {
         getRedirectStrategy().sendRedirect(request, response, userTargetUrl);
      } 
         else if (roles.contains("ROLE_ZEBRA")) {
             getRedirectStrategy().sendRedirect(request, response, zebraTargetUrl);
       
      } else {
         super.onAuthenticationSuccess(request, response, authentication);
         
      }
   }
}
