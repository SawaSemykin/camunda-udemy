package ru.sawasemykin;

import javax.annotation.PostConstruct;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;


/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessUnitTest {

  @Autowired
  private ProcessEngine processEngine;

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @ClassRule
  @Rule
  public static ProcessEngineRule rule;

  @PostConstruct
  void initRule() {
    rule = TestCoverageProcessEngineRuleBuilder.create(processEngine).build();
  }

  @Before
  public void setup() {
    init(processEngine);
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {}

  @Test
  @Deployment(resources = "process.bpmn") // only required for process test coverage
  public void testHappyPath() {
    // Drive the process by API and assert correct behavior by camunda-bpm-assert

    ProcessInstance processInstance = processEngine().getRuntimeService()
        .startProcessInstanceByKey(ProcessConstants.PROCESS_DEFINITION_KEY);

    TaskService taskService = rule.getTaskService();
    Task task = taskService.createTaskQuery().singleResult();
    taskService.complete(task.getId());

    Task task1 = taskService.createTaskQuery().singleResult();
    taskService.complete(task1.getId());

    assertThat(processInstance).isEnded();

  }

}
