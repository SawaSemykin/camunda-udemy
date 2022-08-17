package ru.sawasemykin;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@EnableProcessApplication("order-mgmt")
@RequiredArgsConstructor
public class CamundaApplication implements CommandLineRunner {

  private final RuntimeService runtimeService;

  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    final String businessKey = UUID.randomUUID().toString();

    Map<String, Object> processOrderData = new HashMap<>();
    processOrderData.put("customerName", "John Doe");
    processOrderData.put("item", "mobile");
    processOrderData.put("giftPackagingIsNeeded", true);
    processOrderData.put("zipCode", 123456);

    runtimeService.startProcessInstanceByKey("task_send_process", businessKey, processOrderData);

    Map<String, Object> processOrderDataForShipment = new HashMap<>();
    processOrderDataForShipment.put("customerName", "John Doe");
    processOrderDataForShipment.put("item", "mobile");
    processOrderDataForShipment.put("zipCode", 123456);

    runtimeService.startProcessInstanceByKey("task_receive_process", businessKey, processOrderDataForShipment);
  }
}
