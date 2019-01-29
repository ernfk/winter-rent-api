package com.winterrent.winterrent.service.item;

import com.winterrent.winterrent.dao.item.ItemDAO;
import com.winterrent.winterrent.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private ItemDAO itemDAO;

    @Autowired
    public ItemServiceImpl(ItemDAO theItemDAO) {
        this.itemDAO = theItemDAO;
    }

    @Override
    public List<Item> findAll() {
        logger.info("Finding all items");
        return this.itemDAO.findAll();
    }
}
