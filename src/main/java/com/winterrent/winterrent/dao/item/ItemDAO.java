package com.winterrent.winterrent.dao.item;

import com.winterrent.winterrent.entity.Item;

import java.util.List;

public interface ItemDAO {
    List<Item> findAll();
    Item addItem(Item item);
}
