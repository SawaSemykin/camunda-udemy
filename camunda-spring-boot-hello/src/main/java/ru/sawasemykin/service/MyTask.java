package ru.sawasemykin.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyTask implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("notify invoked");
    }
}
