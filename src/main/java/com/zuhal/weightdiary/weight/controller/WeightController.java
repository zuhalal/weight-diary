package com.zuhal.weightdiary.weight.controller;

import com.zuhal.weightdiary.weight.model.WeightModel;
import com.zuhal.weightdiary.weight.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class WeightController {
    @Autowired
    private WeightService weightService;

    @GetMapping("/")
    public String listObat (Model model) {
        List<WeightModel> listWeight = weightService.getAllWeight();

        model.addAttribute("listWeight", listWeight);
        return "home";
    }
}
