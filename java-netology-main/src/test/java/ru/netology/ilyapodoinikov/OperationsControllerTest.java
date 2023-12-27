package ru.netology.ilyapodoinikov;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.ilyapodoinikov.configuration.OperationProperties;
import ru.netology.ilyapodoinikov.controller.OperationsController;
import ru.netology.ilyapodoinikov.controller.dto.OperationDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsControllerTest extends OperationHistoryApiApplicationTest {

    @Autowired
    OperationsController operationController;

    @Autowired
    OperationProperties properties;

    @Test
    void addNewOperationsTest() throws InterruptedException {
        OperationDTO dto = new OperationDTO(1,1,1000,"RUB","Gold Apple");
        operationController.addOperation(dto);

        Thread.sleep(properties.getTimeout());

        List<OperationDTO> operations = operationController.getOperations(1);
        OperationDTO dto1 = operations.stream().filter(o -> o.getId() == 1).findFirst().get();

        assertEquals(dto1.getId(),dto.getId());
        assertEquals(dto1.getCustomerId(),dto.getCustomerId());
        assertEquals(dto1.getSum(),dto.getSum());
        assertEquals(dto1.getCurrency(),dto.getCurrency());
        assertEquals(dto1.getMerchant(),dto.getMerchant());
    }

    @Test
    void getOperationsTest() throws InterruptedException {
        OperationDTO operation1 = new OperationDTO(2,1,150,"USD","AliExpress");
        operationController.addOperation(operation1);
        Thread.sleep(properties.getTimeout());
        OperationDTO operation2 = new OperationDTO(3,1,2000,"RUB","Monetka");
        operationController.addOperation(operation2);
        Thread.sleep(properties.getTimeout());

        List<OperationDTO> operations = operationController.getOperations(1);
        assertEquals(operation1,operations.get(0));
        assertEquals(operation2, operations.get(1));
    }

    @Test
    void deleteOperationByIdTest() throws InterruptedException {
        OperationDTO operation1 = new OperationDTO(4,1,50,"RUB","Magnit");
        operationController.addOperation(operation1);
        Thread.sleep(properties.getTimeout());
        int oldSize = operationController.getOperations(1).size();


        operationController.deleteperationById(1, 4);
        assertEquals(1, oldSize - operationController.getOperations(1).size());
    }
}