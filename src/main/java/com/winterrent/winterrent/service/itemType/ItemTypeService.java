package com.winterrent.winterrent.service.itemType;

import com.winterrent.winterrent.entity.ItemType;

import java.util.Map;

public interface ItemTypeService {
    Map<Integer, ItemType> getAll();
}
