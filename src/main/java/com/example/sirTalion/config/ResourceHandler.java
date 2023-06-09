package com.example.sirTalion.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class ResourceHandler implements WebMvcConfigurer{
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/")
        .resourceChain(true)
            .addResolver(new PathResourceResolver() {
                @Override
                protected Resource getResource(String resourcePath, Resource location) throws IOException {
                    Resource requestedResource = location.createRelative(resourcePath);
                    if (requestedResource.exists() && requestedResource.isReadable() && requestedResource.getFile().isDirectory()) {
                        return requestedResource;
                    } else {
                        return super.getResource(resourcePath, location);
                    }
                }
            });
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/Frontend/img/**").addResourceLocations("/resources/Frontend/images/");
        registry.addResourceHandler("/Frontend/css/**").addResourceLocations("/resources/Frontend/css/");
        registry.addResourceHandler("/Frontend/js/**").addResourceLocations("/resources/Frontend/js/");
    }
}
