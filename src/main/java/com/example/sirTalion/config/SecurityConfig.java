package com.example.sirTalion.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.sirTalion.oauth.CustomOAuth2User;
import com.example.sirTalion.service.CustomOauth2UserService;
import com.example.sirTalion.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig
{
    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomOauth2UserService oAuth2UserService;

    @Autowired 
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService)
    throws Exception 
    {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder)
        .and()
        .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                    .csrf().disable()
                    .authorizeRequests()
                        .antMatchers("/register").permitAll()
                        .antMatchers("/", "/oauth/**").permitAll()
                        .antMatchers("/home").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/shipper/**").hasRole("SHIPPER")
                        .and()
                    .formLogin()
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(successHandler)
                        .failureUrl("/login?error")
                        .and()
                    .oauth2Login()
                        .loginPage("/login")
                        .userInfoEndpoint()
                            .userService(oAuth2UserService)
                        .and()
                        .successHandler
                        (
                            new AuthenticationSuccessHandler() 
                            {
                                @Override
                                public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
                                        throws IOException, ServletException 
                                {
                                    CustomOAuth2User customOAuth2User = (CustomOAuth2User)authentication.getPrincipal();
                                    customOAuth2User.getAttributes().forEach((k, v) -> 
                                    {
                                        System.out.println(k + "|" + v);
                                    });
                                    String name = customOAuth2User.getName();
                                    String email = customOAuth2User.getEmail();
                                    String address = convertLocaleCode(customOAuth2User.getAttribute("locale"));
                                    userService.processOauth2PostLogin(email, name, address);
                                    response.sendRedirect("/home");
                                }
                            }
                        )
                    .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .and()
                    .rememberMe().key("uniqueAndSecret").rememberMeParameter("remember-me")
                        .and()
                    .exceptionHandling().accessDeniedPage("/login?accessDenied");

        return httpSecurity.build();
    }

    public String convertLocaleCode(String locale)
    {
        switch(locale)
        {
            case "vi": return "Vietnam";
            case "en-us": return "UnitedState";
            case "zh": return "China";
            case "ja": return "Japan";
            default: return "Vietnam";
        }
    }
}
