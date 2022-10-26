package ru.sawasemykin.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sawasemykin.model.Employee;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "employee-leave")
public class EmployeeLeaveController {

    private static final String PROCESS_DEFINITION_KEY = "employee_leave";

    @PostMapping
    public ResponseEntity<HttpStatus> startEmployeeLeaveProcess(@RequestBody Employee employee) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> processVariable = new HashMap<>();
        processVariable.put("employeeId", employee.getId());
        processVariable.put("employeeName", employee.getName());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, processVariable);

        log.info("Process instance {} started", processInstance.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
