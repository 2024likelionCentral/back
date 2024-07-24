package com.example.metalog.controller;

import com.example.metalog.entity.Circumstance;
import com.example.metalog.service.CircumstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/circumstances")
public class CircumstanceController {

    private final CircumstanceService service;

    @Autowired
    public CircumstanceController(CircumstanceService service) {
        this.service = service;
    }

    @PostMapping
    public Circumstance submitCircumstance(@RequestBody Circumstance circumstance) {
        return service.saveCircumstance(circumstance);
    }
}


//
//import com.example.metalog.entity.Circumstance;
//import org.springframework.stereotype.RestController;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//@RestController
//@RequestMapping("/log")
//@SessionAttributes("circumstance")
//public class CircumstanceController {
//
//    @ModelAttribute("circumstance")
//    public Circumstance circumstance(){
//        return new Circumstance();
//    }
//
//    @GetMapping("/step1")
//    public String step1(Model model) {
//        return "step1";
//    }
//
//    @PostMapping("/step1")
//    public String saveStep1(@ModelAttribute Circumstance circumstance, @RequestParam String situation) {
//        circumstance.setSituation(situation);
//        return "redirect:/log/step2";
//    }
//
//    @GetMapping("/step2")
//    public String step2(Model model) {
//        return "step2";
//    }
//
//    @PostMapping("/step2")
//    public String saveStep2(@ModelAttribute Circumstance circumstance, @RequestParam String feeling) {
//        circumstance.setFeeling(feeling);
//        return "redirect:/log/step3";
//    }
//
//    @GetMapping("/step3")
//    public String step3(Model model) {
//        return "step3";
//    }
//
//    @PostMapping("/step3")
//    public String saveStep3(@ModelAttribute Circumstance circumstance, @RequestParam String cause) {
//        circumstance.setCause(cause);
//        return "redirect:/log/summary";
//    }
//
//    @GetMapping("/summary")
//    public String summary(@ModelAttribute Circumstance circumstance, Model model) {
//        model.addAttribute("circumstance", circumstance);
//        return "summary";
//    }
//
//    @PostMapping("/save")
//    public String save(@ModelAttribute Circumstance circumstance, Model model) {
//        // 저장 로직 추가
//        model.addAttribute("circumstance", circumstance);
//        return "result";
//    }
//}
