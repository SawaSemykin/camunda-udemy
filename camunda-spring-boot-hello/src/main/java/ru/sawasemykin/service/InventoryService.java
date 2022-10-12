package ru.sawasemykin.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        final String productCode = delegateExecution.hasVariable("productCode")
                ? (String) delegateExecution.getVariable("productCode") : null;

        int productCodeCount = 0; // inventory service stub
        delegateExecution.setVariable("productCodeCount", productCodeCount);

        String errorCode = "anotherCode";
        if (productCodeCount <= 0) {
            errorCode = "productCodeUnavailable"; // comment this line to change process flow
            String errorMessage = "This product is unavailable";
            log.info("product code {} is unavailable", productCode);
            throw new BpmnError(errorCode, errorMessage);
        }
    }
}
