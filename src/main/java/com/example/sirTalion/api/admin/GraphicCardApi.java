package com.example.sirTalion.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirTalion.entities.GraphicCard;
import com.example.sirTalion.entities.enumerate.GraphicCardType;
import com.example.sirTalion.repository.GraphicCardRepository;

@RestController
@RequestMapping("/api/graphic-card")
public class GraphicCardApi 
{
    @Autowired
    private GraphicCardRepository graphicCardRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/type")
    public GraphicCardType[] getAllGraphicCardTypes()
    {
        return GraphicCardType.values();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<GraphicCard> getAllGraphicCards()
    {
        return graphicCardRepository.findAll();
    }
}
