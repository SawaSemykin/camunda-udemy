package ru.sawasemykin.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderInventoryService implements JavaDelegate {

    public static int retryCount = 0;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        retryCount++;
        delegateExecution.setVariable("retryCount", retryCount);

        log.info("Service invoked. Retry count: {}", retryCount);
        if (retryCount <= 3) {
            delegateExecution.setVariable("isAvailable", false);
            log.info("waiting for 20s before throwing the exception");
            Thread.sleep(20_000);
            throw new RuntimeException("Service not available"); // rest call failed
        } else {
            delegateExecution.setVariable("isAvailable", true);
            retryCount = 0;
        }
    }
}
