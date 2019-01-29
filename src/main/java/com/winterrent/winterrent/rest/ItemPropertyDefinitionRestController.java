package com.winterrent.winterrent.rest;

import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.service.ItemPropertyDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemPropertyDefinitionRestController {

    private ItemPropertyDefinitionService itemPropertyDefinitionService;

    @Autowired
    public ItemPropertyDefinitionRestController(ItemPropertyDefinitionService theItemPropertyDefinitionService) {
        this.itemPropertyDefinitionService = theItemPropertyDefinitionService;
    }

    @GetMapping("/itemPropertyDefinitions")
    List<ItemPropertyDefinition> findAll() {
        return this.itemPropertyDefinitionService.findAll();
    }

    @GetMapping("/itemPropertyDefinitions/itemType/{itemTypeId}")
    List<ItemPropertyDefinition> findByItemTypeId(@PathVariable int itemTypeId) {
        return this.itemPropertyDefinitionService.findByItemTypeId(itemTypeId);
    }
}
