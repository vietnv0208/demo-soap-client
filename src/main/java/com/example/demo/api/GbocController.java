package com.example.demo.api;

import com.viettel.bccs.boc2.service.ProgramBO;
import com.viettel.bccs.boc2.service.ReportGroupKpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GbocController  implements GbocIService {
    @Autowired
    GbocServiceImpl gbocService;


    @Override
    public List<ProgramBO> getProgramsSME(String token) {
        return gbocService.getProgramsSME();
    }

    @Override
    public List<ReportGroupKpi> getReportListGroupKpi(String token) {
        return gbocService.getReportListGroupKpi();
    }
}
