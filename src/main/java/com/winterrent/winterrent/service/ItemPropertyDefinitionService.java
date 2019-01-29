package com.winterrent.winterrent.service;

import com.winterrent.winterrent.entity.ItemPropertyDefinition;

import java.util.List;

public interface ItemPropertyDefinitionService {
    List<ItemPropertyDefinition> findAll();
    List<ItemPropertyDefinition> findByItemTypeId(int itemTypeId);
}
