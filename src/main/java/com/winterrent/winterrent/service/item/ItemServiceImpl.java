package com.winterrent.winterrent.service.item;

import com.winterrent.winterrent.dao.item.ItemDAO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.rest.exceptions.ItemNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    private ItemDAO itemDAO;

    @Autowired
    public ItemServiceImpl(ItemDAO theItemDAO) {
        this.itemDAO = theItemDAO;
    }

    @Override
    public List<Item> findAll() {
        LOGGER.info("Finding all items");
        return this.itemDAO.findAll();
    }

    @Override
    public Item addItem(Item item) {
        String modelNo = generateModelNo(item);
        item.setModelNo(modelNo);

        LOGGER.info("Adding new item");
        return this.itemDAO.saveOrUpdate(item);
    }

    private String generateModelNo(Item item) {
        String model = Objects.requireNonNull(item.getItemProperties()
                .stream()
                .filter(itemProperty ->
                        itemProperty
                                .getItemPropertyDefinition()
                                .getPropertyName()
                                .toLowerCase()
                                .equals("model")
                )
                .findFirst()
                .orElse(null))
                .getValue();

        String modelFirstLetter = String.valueOf(model.charAt(0));
        String currentTime = String.valueOf(new Date().getTime());
        String uniqueNo = currentTime.substring(currentTime.length() - 5);
        String modelNo = modelFirstLetter + uniqueNo;

        LOGGER.info("Generated model number {} for new item", modelNo);

        return modelNo;
    }

    @Override
    public Item findItem(int itemId) {
        LOGGER.info("Finding item with id: {}", itemId);
        Optional<Item> item = this.itemDAO.findItem(itemId);
        return item.orElseThrow(() -> new ItemNotFound("The item with id: " + itemId + " was not found"));
    }

    @Override
    public void deleteItem(int itemId) {
        LOGGER.info("Deleting item with id: {}", itemId);
        Optional<Item> item = this.itemDAO.findItem(itemId);

        item.ifPresentOrElse(
                i -> this.itemDAO.deleteItem(itemId),
                () -> {
                    throw new ItemNotFound("Couldn't delete item. Item with id: " + itemId + " was not found.");
                });

    }

    @Override
    public Item updateItem(Item item) {
        LOGGER.info("Editing item with id: {}", item.getId());
        return this.itemDAO.saveOrUpdate(item);
    }
}
