package com.winterrent.winterrent.rest.itemProperty;

import com.winterrent.winterrent.entity.ItemType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ItemTypesRestController {

    @GetMapping("/itemTypes")
    Map<Integer, ItemType> getAll() {
        ItemType[] itemTypesArr = ItemType.values();
        Map<Integer, ItemType> itemTypes = new HashMap<>();
        int counter = 0;

        for (ItemType itemType : itemTypesArr) {
            itemTypes.put(counter, itemType);
            counter++;
        }

        return itemTypes;
    }
}
