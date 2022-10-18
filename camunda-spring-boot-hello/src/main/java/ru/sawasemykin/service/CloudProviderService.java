package ru.sawasemykin.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class CloudProviderService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int cost = Optional.of(delegateExecution)
                .map(e -> e.getVariable("cost"))
                .map(Object::toString)
                .map(Integer::valueOf)
                .orElseThrow(RuntimeException::new);
        log.info("Cloud provider cost: {}", cost);

    }
}
