package ru.sawasemykin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("orderSender")
public class OrderSenderDelegate implements JavaDelegate {

    private final RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Business key: {}. {}.execute() is invoked", execution.getBusinessKey(), OrderSenderDelegate.class.getName());

        MessageCorrelationResult result = runtimeService.createMessageCorrelation("messageShipment")
                .processInstanceBusinessKey(execution.getBusinessKey())
                .setVariable("caller", OrderSenderDelegate.class.getName())
                .correlateWithResult();
        DelegateExecution shipmentExecution = (DelegateExecution) result.getExecution();

        log.info("order shipment context\n{}", shipmentExecution.getVariables());
    }
}
