package kigali.rw.pocketmoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kigali.rw.pocketmoney.service.DashboardService;

@RestController 
@RequestMapping ("/api/dashboard")
public class DashboardController {

    @Autowired 
    private DashboardService dashboardService;

    @GetMapping ("/summary")
    public ResponseEntity<?> getSummary(){
        return ResponseEntity.ok(dashboardService.getSummary());
    }
    

}
