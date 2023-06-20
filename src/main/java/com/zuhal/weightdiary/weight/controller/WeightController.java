package com.zuhal.weightdiary.weight.controller;

import com.zuhal.weightdiary.weight.model.WeightModel;
import com.zuhal.weightdiary.weight.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/")
public class WeightController {
    @Autowired
    private WeightService weightService;

    @GetMapping("/")
    public String listObat(Model model) {
        List<WeightModel> listWeight = weightService.getAllWeight();

        model.addAttribute("listWeight", listWeight);
        return "home";
    }

    @GetMapping("/update/{id}")
    public String updateWeightPage(@PathVariable String id, Model model) {
        WeightModel weight = weightService.getWeight(Long.parseLong(id));
        model.addAttribute("weight", weight);
        model.addAttribute("isEdit", true);

        return "update-weight-form";
    }

    @PostMapping(value = "/update/{id}", params = {"save"})
    public String updateWeightSubmitPage(@ModelAttribute WeightModel weightModel, @PathVariable String id, RedirectAttributes redirectAttrs) {
        WeightModel weight = weightService.getWeight(Long.parseLong(id));

        weight.setMin(weightModel.getMin());
        weight.setMax(weightModel.getMax());
        weight.setDate(weightModel.getDate());

        weightService.updateWeight(weight);
        redirectAttrs.addFlashAttribute("success", "Berat Badan berhasil diubah");
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addWeightPage(Model model) {
        WeightModel weight = new WeightModel();
        model.addAttribute("weight", weight);
        model.addAttribute("isEdit", false);

        return "update-weight-form";
    }

    @PostMapping(value = "/add", params = {"save"})
    public String addWeightSubmitPage(@ModelAttribute WeightModel weightModel, RedirectAttributes redirectAttrs) {
        weightModel.setCreatedAt(LocalDateTime.now());

        weightService.createWeight(weightModel);
        redirectAttrs.addFlashAttribute("success", "Berat Badan berhasil dibuat");
        return "redirect:/";
    }
}
