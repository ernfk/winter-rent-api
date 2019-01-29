package com.winterrent.winterrent.rest.item;

import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemRestController {

    private ItemService itemService;

    @Autowired
    public ItemRestController(ItemService theItemService) {
        this.itemService = theItemService;
    }

    @GetMapping("/items")
    List<Item> findAll() {
        return this.itemService.findAll();
    }
}
