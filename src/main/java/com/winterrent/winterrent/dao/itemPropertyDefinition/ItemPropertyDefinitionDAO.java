package com.winterrent.winterrent.dao.itemPropertyDefinition;

import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.entity.ItemType;

import java.util.List;

public interface ItemPropertyDefinitionDAO {
    List<ItemPropertyDefinition> findAll();
    List<ItemPropertyDefinition> findByItemTypeId(ItemType itemType);
}
