package com.winterrent.winterrent.service.itemType;

import com.winterrent.winterrent.entity.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemTypeServiceImpl.class);

    @Override
    public List<ItemType> getAll() {
        LOGGER.info("Getting item types");
        return Arrays.asList(ItemType.values());
    }
}
