package com.winterrent.winterrent.rest.itemProperty;

import com.winterrent.winterrent.entity.ItemType;
import com.winterrent.winterrent.service.itemType.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ItemTypeRestController {

    private ItemTypeService itemTypeService;

    @Autowired
    public ItemTypeRestController(ItemTypeService theItemTypeService) {
        this.itemTypeService = theItemTypeService;
    }

    @GetMapping("/itemTypes")
    Map<Integer, ItemType> getAll() {
        return this.itemTypeService.getAll();
    }
}
