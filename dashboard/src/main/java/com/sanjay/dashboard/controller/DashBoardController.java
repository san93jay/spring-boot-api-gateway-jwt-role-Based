package com.sanjay.dashboard.controller;

import com.sanjay.dashboard.dto.DashBoardDTO;
import com.sanjay.dashboard.response.UnifiedResponse;
import com.sanjay.dashboard.service.DashBoradService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanjay Vishwakarma
 */
@RestController
@RequestMapping("/v1/dashBoard")
public class DashBoardController {
    @Autowired
    private DashBoradService dashBoardService;

    @Operation(summary = "1. DashBoard Request")
    @GetMapping("/getDashBoard")
   /* public UnifiedResponse<DashBoardDTO> getDashBoard(){
        return dashBoardService.getDashBoard();
    }*/
    public String getDashBoard(){
        return "dashBoard";
    }
}
