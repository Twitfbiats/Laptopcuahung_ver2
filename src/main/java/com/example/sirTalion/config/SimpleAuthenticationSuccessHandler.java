package com.example.sirTalion.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException 
    {
        @SuppressWarnings("unchecked")
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>)authentication.getAuthorities();

        int flag = 0;
        for (int i=0;i<grantedAuthorities.size();i++)
        {
            if (grantedAuthorities.get(i).getAuthority().equals("ROLE_ADMIN"))
            {
                flag = flag|1; // XOR bit
            }
            if (grantedAuthorities.get(i).getAuthority().equals("ROLE_SHIPPER"))
            {
                flag = flag|2;
            }
            if (grantedAuthorities.get(i).getAuthority().equals("ROLE_MEMBER"))
            {
                flag = flag|4;
            }
        }

        if (flag == 1 || flag == 3 || flag == 5 || flag == 7) redirectStrategy.sendRedirect(request, response, "/admin");
        else
        {
            if (flag == 2 || flag == 6) redirectStrategy.sendRedirect(request, response, "/shipper");
            else redirectStrategy.sendRedirect(request, response, "/home");
        }
    }
    
}
