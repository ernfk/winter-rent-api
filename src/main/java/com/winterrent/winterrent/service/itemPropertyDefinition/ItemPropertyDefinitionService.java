package com.winterrent.winterrent.service.itemPropertyDefinition;

import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.entity.ItemType;

import java.util.List;

public interface ItemPropertyDefinitionService {
    List<ItemPropertyDefinition> findAll();
    List<ItemPropertyDefinition> findByItemType(ItemType itemType);
}
