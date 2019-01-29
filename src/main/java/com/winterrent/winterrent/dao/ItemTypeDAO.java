package com.winterrent.winterrent.dao;

import com.winterrent.winterrent.entity.ItemType;

import java.util.List;

public interface ItemTypeDAO {
    public List<ItemType> findAll();
}
