package ru.sawasemykin.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderValidatorService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        boolean isValidated = true;
        delegateExecution.setVariable("isValidated", isValidated);
        log.info("Order validation status: {}", isValidated);
        for (int i = 0; i < 5; i++) {
            log.info("waiting...");
            Thread.sleep(3_000);
        }
    }
}
