package com.winterrent.winterrent.rest.itemProperty;

import com.winterrent.winterrent.entity.ItemProperty;
import com.winterrent.winterrent.service.itemProperty.ItemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemPropertyRestController {
    private ItemPropertyService itemPropertyService;

    @Autowired
    public ItemPropertyRestController(ItemPropertyService theItemPropertyService) {
        this.itemPropertyService = theItemPropertyService;
    }

    @GetMapping("/itemProperties")
    List<ItemProperty> findAll() {
        return this.itemPropertyService.findAll();
    }
}
