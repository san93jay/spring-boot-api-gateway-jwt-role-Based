package com.sanjay.experience.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanjay Vishwakarma
 */
@RestController
@RequestMapping("/v1/experience")
public class ExperienceController {


    @Operation(summary = "1. experience Request")
    @GetMapping("/getExperience")
    public String getExperience(){
        return "Experience";
    }
}
