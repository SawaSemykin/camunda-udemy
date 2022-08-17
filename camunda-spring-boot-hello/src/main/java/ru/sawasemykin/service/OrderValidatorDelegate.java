package ru.sawasemykin.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Optional;

@Slf4j
public class OrderValidatorDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Business key: {}. Start validating order...", delegateExecution.getBusinessKey());

        String order = (String) delegateExecution.getVariable("order");
        boolean isValid = Optional.ofNullable(order)
                .filter(o -> !o.isEmpty())
                .isPresent();
        delegateExecution.setVariable("isValid", isValid);

        log.info("Business key: {}. End validating order. Order is valid: {}", delegateExecution.getBusinessKey(), isValid);
    }
}
