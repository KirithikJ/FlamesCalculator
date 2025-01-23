package com.example.flames.controller;

import com.example.flames.model.FlamesResult;
import com.example.flames.service.FlamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlamesController {

    @Autowired
    private FlamesService flamesService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateFlames(
            @RequestParam String name1,
            @RequestParam String name2,
            Model model) {
        String relationship = flamesService.calculateFlames(name1, name2);

        FlamesResult result = new FlamesResult();
        result.setName1(name1);
        result.setName2(name2);
        result.setRelationship(relationship);

        model.addAttribute("result", result);
        return "result";
    }
}
