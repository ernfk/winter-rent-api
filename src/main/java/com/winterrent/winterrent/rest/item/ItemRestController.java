package com.winterrent.winterrent.rest.item;

import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/items")
    Item addItem(@RequestBody Item item) {
        item.setId(0);
        return this.itemService.addItem(item);
    }

    @GetMapping("items/{itemId}")
    Item findItem(@PathVariable int itemId) {
        return this.itemService.findItem(itemId);
    }

    @DeleteMapping("items/{itemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteItem(@PathVariable int itemId) {
        this.itemService.deleteItem(itemId);
    }

    @PutMapping("/items")
    Item updateItem(@RequestBody Item item) {
        return this.itemService.updateItem(item);
    }
}
