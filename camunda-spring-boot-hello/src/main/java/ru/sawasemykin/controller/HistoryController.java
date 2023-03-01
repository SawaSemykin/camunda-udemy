package ru.sawasemykin.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value="history")
public class HistoryController {

    @PostMapping
    public void cleanupHistory() {
        log.info("cleanup history...");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().finished();
        long foundProcessesCount = query.count();
        log.info("Found {} finished processes", foundProcessesCount);
        if (query.count() != 0) {
            historyService.deleteHistoricProcessInstancesAsync(query, "manually ran");
        }
    }
}
