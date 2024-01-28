package org.yuenio.droolSandbox.controller;

import org.yuenio.droolSandbox.model.Order;
import org.yuenio.droolSandbox.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/getDiscount")
    public Order getDiscount (@RequestBody Order order) throws FileNotFoundException{
        return orderService.getDiscountForOrderV2(order);
    }
}
