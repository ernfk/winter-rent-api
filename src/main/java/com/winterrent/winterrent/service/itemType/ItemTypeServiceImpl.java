package com.winterrent.winterrent.service.itemType;

import com.winterrent.winterrent.entity.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    private static final Logger logger = LoggerFactory.getLogger(ItemTypeServiceImpl.class);

    @Override
    public Map<Integer, ItemType> getAll() {
        logger.info("Getting item types");

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
