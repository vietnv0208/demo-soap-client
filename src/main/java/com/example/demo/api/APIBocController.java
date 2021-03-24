package com.example.demo.api;

import com.example.demo.service.GbocServiceImpl;
import com.viettel.bccs.boc2.service.Exception_Exception;
import com.viettel.bccs.boc2.service.WsResponseCrmApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class APIBocController {
    @Autowired
    GbocServiceImpl gbocService;

    @GetMapping("getProgramsSME")
    public WsResponseCrmApp getProgramsSME(){
        try {
            return gbocService.getProgramsSME();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("getReportListGroupKpi")
    public WsResponseCrmApp getReportListGroupKpi(){
        try {
            return gbocService.getReportListGroupKpi();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
