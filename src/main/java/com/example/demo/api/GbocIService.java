package com.example.demo.api;

import com.viettel.bccs.boc2.service.ProgramBO;
import com.viettel.bccs.boc2.service.ReportGroupKpi;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface GbocIService {

    @RequestMapping(value = "getProgramsSME", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    List<ProgramBO> getProgramsSME(@RequestParam("token") String token);

    @RequestMapping(value = "getReportListGroupKpi", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    List<ReportGroupKpi> getReportListGroupKpi(@RequestParam("token") String token);

}
