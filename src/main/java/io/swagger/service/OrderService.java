package io.swagger.service;

import io.swagger.entity.OrderEntity;
import io.swagger.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity saveOrder(OrderEntity order) {
        order.setStatus("Pending");
        return orderRepository.save(order);
    }

    public Optional<OrderEntity> updateOrderStatus(Integer id, String newStatus) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        optionalOrder.ifPresent(order -> {
            order.setStatus(newStatus);
            orderRepository.save(order);
        });
        return optionalOrder;
    }
}
