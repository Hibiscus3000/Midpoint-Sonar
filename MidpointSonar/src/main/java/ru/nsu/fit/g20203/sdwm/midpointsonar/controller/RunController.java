package ru.nsu.fit.g20203.sdwm.midpointsonar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPoint;

@RestController
@RequestMapping(value = "/run/", produces = MediaType.TEXT_PLAIN_VALUE)
public class RunController {

//    @Autowired
//    private QualityProfileRunner qualityProfileRunner;
//
//    @PutMapping("qp")
//    public String runQP(@RequestParam String name) {
//        service = MidPoint.getService()
//        result = qualityProfileRunner.runQP(service, name);
//        runHistory.add(result);
//        result.addCallback(updateRunHistory());
//        return formatter.formatQPRunResult(result);
//    }
}
