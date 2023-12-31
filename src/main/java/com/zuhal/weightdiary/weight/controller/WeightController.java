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

        Long sumMaxAverage = 0L;
        Long sumMinAverage = 0L;
        Long sumDiffAverage = 0L;

        for (int i = 0; i < listWeight.size(); i++) {
            sumMaxAverage += listWeight.get(i).getMax();
            sumMinAverage += listWeight.get(i).getMin();
            sumDiffAverage += (listWeight.get(i).getMax() - listWeight.get(i).getMin());
        }

        model.addAttribute("listWeight", listWeight);
        model.addAttribute("averageMax", sumMaxAverage / listWeight.size());
        model.addAttribute("averageMin", sumMinAverage / listWeight.size());
        model.addAttribute("averageDiff", sumDiffAverage / listWeight.size());

        return "home";
    }

    @GetMapping("/detail/{id}")
    public String detailWeightPage(@PathVariable String id, Model model) {
        WeightModel weight = weightService.getWeight(Long.parseLong(id));
        model.addAttribute("weight", weight);

        return "detail-weight";
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

        if (weightModel.getMin() > weightModel.getMax()) {
            redirectAttrs.addFlashAttribute("error", "Berat minimal tidak boleh melebihi berat maksimal");
            return "redirect:/update/" + id;
        }

        weightService.updateWeight(weight);
        redirectAttrs.addFlashAttribute("success", "Berat Badan berhasil diubah");
        return "redirect:/";
    }

    @PostMapping(value = "/delete/{id}", params = {"delete"})
    public String deleteWeight(@ModelAttribute WeightModel weightModel, @PathVariable String id, RedirectAttributes redirectAttrs) {
        WeightModel weight = weightService.getWeight(Long.parseLong(id));

        weightService.deleteWeight(weight);
        redirectAttrs.addFlashAttribute("success", "Berat Badan berhasil dihapus");
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

        if (weightModel.getMin() > weightModel.getMax()) {
            redirectAttrs.addFlashAttribute("error", "Berat minimal tidak boleh melebihi berat maksimal");
            return "redirect:/add";
        }

        weightService.createWeight(weightModel);
        redirectAttrs.addFlashAttribute("success", "Berat Badan berhasil dibuat");
        return "redirect:/";
    }
}
