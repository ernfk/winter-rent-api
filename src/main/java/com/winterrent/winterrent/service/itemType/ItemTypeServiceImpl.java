package com.winterrent.winterrent.service.itemType;

import com.winterrent.winterrent.dao.itemType.ItemTypeDAO;
import com.winterrent.winterrent.entity.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    private static final Logger logger = LoggerFactory.getLogger(ItemTypeServiceImpl.class);

    private ItemTypeDAO itemTypeDAO;

    @Autowired
    public ItemTypeServiceImpl(ItemTypeDAO theItemTypeDAO) {
        itemTypeDAO = theItemTypeDAO;
    }

    @Override
    public List<ItemType> findAll() {
        logger.info("Finding all item types");
        return itemTypeDAO.findAll();
    }
}
