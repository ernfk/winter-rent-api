package com.winterrent.winterrent.service.item;

import com.winterrent.winterrent.dao.item.ItemDAO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.service.item.exceptions.ItemNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Item addItem(Item item) {
        logger.info("Adding new item");
        return this.itemDAO.addItem(item);
    }

    @Override
    public Item findItem(int itemId) {
        logger.info("Finding item with id: {}", itemId);
        Optional<Item> item = this.itemDAO.findItem(itemId);
        return item.orElseThrow(() -> new ItemNotFound("The item with id: " + itemId + " was not found"));
    }

    @Override
    public void deleteItem(int itemId) {
        logger.info("Deleting item with id: {}", itemId);
        Optional<Item> item = this.itemDAO.findItem(itemId);

        item.ifPresentOrElse(
                i -> this.itemDAO.deleteItem(itemId),
                () -> {
                    throw new ItemNotFound("Couldn't delete item. Item with id: " + itemId + " was not found.");
                });

    }
}
