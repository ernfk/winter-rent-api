package com.winterrent.winterrent.dao.item;

import com.winterrent.winterrent.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDAO {
    List<Item> findAll();
    Item addItem(Item item);
    Optional<Item> findItem(int itemId);
    void deleteItem(int itemId);
    Item updateItem(Item item);
}
