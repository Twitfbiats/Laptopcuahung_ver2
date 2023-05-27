package com.example.sirTalion.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User
{
    private OAuth2User oauth2User;

    public CustomOAuth2User(OAuth2User oAuth2User)
    {
        this.oauth2User = oAuth2User;
    }

    @Override
    public Map<String, Object> getAttributes() 
    {
        return oauth2User.getAttributes();
    }

    // how did oauth2 user get authorites though ???
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() 
    {
        return oauth2User.getAttribute("name");
    }

    public String getEmail()
    {
        return oauth2User.<String>getAttribute("email");
    }
}

// Here, this class wraps an instance of OAuth2User class
// , which will be passed by Spring OAuth upon successful 
// OAuth authentication. And note that we override the 
// getName() and code the getEmail() methods to return 
// username and email, respectively.