package ru.sawasemykin;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.*;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() {
    // Drive the process by API and assert correct behavior by camunda-bpm-assert

    Mocks.register("logger", new LoggerDelegate());

    ProcessInstance processInstance = processEngine().getRuntimeService()
        .startProcessInstanceByKey(ProcessConstants.PROCESS_DEFINITION_KEY);

    assertThat(processInstance).isEnded();
  }

  @Test
  @Deployment(resources = "bpmns/ruf-noind-contragents-limit.bpmn")
  public void parseAndDeploymentTest() {
  }

//  @Test
//  @Deployment(resources = "bpmns/ruf-noind-contragents-limit-decision.dmn")
//  public void parseAndDeploymentTest2() {
//  }
//
//  @Test
//  @Deployment(resources = "dmns/f-pa-enter-no-ind-limit-calculation-decision.dmn")
//  public void parseAndDeploymentTest3() {
//  }
}
