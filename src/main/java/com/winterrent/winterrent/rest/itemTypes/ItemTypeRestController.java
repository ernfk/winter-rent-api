package com.winterrent.winterrent.rest.itemTypes;

import com.winterrent.winterrent.entity.ItemType;
import com.winterrent.winterrent.service.itemType.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        this.itemTypeService = theItemTypeService;
    }

    @GetMapping("/itemTypes")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<ItemType> getAll() {
        return this.itemTypeService.getAll();
    }
}
