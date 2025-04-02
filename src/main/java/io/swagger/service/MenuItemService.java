package io.swagger.service;

import io.swagger.entity.MenuItemEntity;
import io.swagger.model.MenuItem;
import io.swagger.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository repository;

    public void addMenuItem(MenuItem dto) {
        MenuItemEntity item = new MenuItemEntity();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setCategory(dto.getCategory());
        repository.save(item);
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll().stream().map(entity -> {
            MenuItem dto = new MenuItem();
            dto.setId(entity.getId().intValue());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setCategory(entity.getCategory());
            return dto;
        }).collect(Collectors.toList());
    }
}
