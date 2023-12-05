package com.example.sirTalion.controller;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileController 
{
    @Autowired ServletContext servletContext;

    @GetMapping("/img/product{id}")
    public String imageListing(@PathVariable long id, Model model)
    {
        try 
        {
            String path = servletContext.getRealPath("resources/images/");

            List<String> files = Stream.of
            (new File(path + "product" + id).listFiles())
            .filter(file -> !file.isDirectory())
            .map(File::getName)
            .toList();    

            model.addAttribute("files", files);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "image-list";
    }

    @GetMapping("/img/product{id}_360")
    public String image360Listing(@PathVariable long id, Model model)
    {
        try 
        {
            String path = servletContext.getRealPath("resources/images/");

            List<String> files = Stream.of
            (new File(path + "product" + id + "_360").listFiles())
            .filter(file -> !file.isDirectory())
            .map(File::getName)
            .toList();    

            model.addAttribute("files", files);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "image-list";
    }
}
