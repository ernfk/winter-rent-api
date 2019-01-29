package com.winterrent.winterrent.rest.itemType;

import com.winterrent.winterrent.entity.ItemType;
import com.winterrent.winterrent.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemTypeRestController {
    private ItemTypeService itemTypeService;

    @Autowired
    public ItemTypeRestController(ItemTypeService theItemTypeService) {
        itemTypeService = theItemTypeService;
    }

    @GetMapping("/itemTypes")
    public List<ItemType> findAll() {
        return itemTypeService.findAll();
    }

}
