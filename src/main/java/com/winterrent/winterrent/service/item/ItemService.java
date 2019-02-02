package com.winterrent.winterrent.service.item;

import com.winterrent.winterrent.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item addItem(Item item);
    Item findItem(int itemId);
}
