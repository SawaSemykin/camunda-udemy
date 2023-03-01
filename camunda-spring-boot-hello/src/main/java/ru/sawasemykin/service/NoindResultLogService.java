package ru.sawasemykin.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.sawasemykin.model.DocTypeName;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class NoindResultLogService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        List<Map<String, Double>> resultMapList = (List<Map<String, Double>>) delegateExecution.getVariable("resultMap");
        List<DocTypeName> docTypeNames = (List<DocTypeName>) delegateExecution.getVariable("docTypeNameList");

        log.info("result map list: {}", resultMapList);

    }
}
