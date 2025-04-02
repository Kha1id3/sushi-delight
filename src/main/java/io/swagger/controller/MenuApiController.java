package io.swagger.controller;

import io.swagger.api.MenuApi;
import io.swagger.model.MenuItem;
import io.swagger.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuApiController implements MenuApi {

    @Autowired
    private MenuItemService service;

    @Override
    public ResponseEntity<List<MenuItem>> menuGet() {
        return ResponseEntity.ok(service.getAllMenuItems());
    }

    @Override
    public ResponseEntity<Void> menuPost(MenuItem menuItem) {
        service.addMenuItem(menuItem);
        return ResponseEntity.status(201).build();
    }
}
