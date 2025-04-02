package io.swagger.controller;

import io.swagger.api.OrdersApi;
import io.swagger.model.NewOrder;
import io.swagger.model.Order;
import io.swagger.model.OrderStatusUpdate;
import io.swagger.entity.OrderEntity;
import io.swagger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrdersApiController implements OrdersApi {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<List<Order>> ordersGet() {
        List<OrderEntity> entities = orderService.getAllOrders();

        List<Order> dtos = entities.stream().map(entity -> {
            Order dto = new Order();
            dto.setId(entity.getId());
            dto.setCustomerName(entity.getCustomerName());
            dto.setItems(entity.getItems());
            dto.setNotes(entity.getNotes());
            dto.setStatus(entity.getStatus());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<Void> ordersPost(NewOrder newOrder) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerName(newOrder.getCustomerName());
        entity.setItems(newOrder.getItems());
        entity.setNotes(newOrder.getNotes());

        orderService.saveOrder(entity);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Void> ordersOrderIdStatusPatch(Integer orderId, OrderStatusUpdate update) {
        if (orderService.updateOrderStatus(orderId, update.getStatus()).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
