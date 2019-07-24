package com.winterrent.winterrent.rest.itemPropertyDefinition;

import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.entity.ItemType;
import com.winterrent.winterrent.service.itemPropertyDefinition.ItemPropertyDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<ItemPropertyDefinition> findAll() {
        return this.itemPropertyDefinitionService.findAll();
    }

    @GetMapping("/itemPropertyDefinitions/itemType/{itemType}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<ItemPropertyDefinition> findByItemType(@PathVariable ItemType itemType) {
        return this.itemPropertyDefinitionService.findByItemType(itemType);
    }

}
