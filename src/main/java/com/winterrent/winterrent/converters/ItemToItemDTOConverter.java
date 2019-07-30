package com.winterrent.winterrent.converters;

import com.winterrent.winterrent.dto.ItemDTO;
import com.winterrent.winterrent.dto.ItemPropertyDTO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.entity.ItemProperty;
import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.entity.ItemType;

import java.util.List;
import java.util.stream.Collectors;

public class ItemToItemDTOConverter implements GenericConverter<Item, ItemDTO> {

    @Override
    public ItemDTO createFromEntity(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setModelNo(item.getModelNo());
        itemDTO.setItemType(item.getItemType());
        itemDTO.setItemProperties(getItemPropertiesDtos(item.getItemProperties()));
        return itemDTO;
    }

    private List<ItemPropertyDTO> getItemPropertiesDtos(List<ItemProperty> itemProperties) {
        return itemProperties
                .stream()
                .map(itemProperty -> {
                    ItemPropertyDTO itemPropertyDTO = new ItemPropertyDTO();
                    itemPropertyDTO.setId(itemProperty.getId());
                    itemPropertyDTO.setProperty(itemProperty.getItemPropertyDefinition().getPropertyName());
                    itemPropertyDTO.setValue(itemProperty.getValue());
                    itemPropertyDTO.setPropertyId(itemProperty.getItemPropertyDefinition().getId());
                    return itemPropertyDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Item createFromDTO(ItemDTO dto) {
        Item item = new Item();
        List<ItemProperty> itemProperties = getItemProperties(dto.getItemProperties(), dto.getItemType(), item);

        item.setId(dto.getId());
        item.setModelNo(dto.getModelNo());
        item.setItemType(dto.getItemType());
        item.setItemProperties(itemProperties);

        return item;
    }

    private List<ItemProperty> getItemProperties(List<ItemPropertyDTO> itemPropertiesDtos, ItemType type, Item item) {
        return itemPropertiesDtos
                .stream()
                .map(itemPropertyDto -> {
                    ItemProperty itemProperty = new ItemProperty();
                    itemProperty.setId(itemPropertyDto.getId());
                    itemProperty.setItemPropertyDefinition(getItemPropertyDefinition(itemPropertyDto, type));
                    itemProperty.setValue(itemPropertyDto.getValue());
                    itemProperty.setItemType(type);
                    itemProperty.setItem(item);
                    return itemProperty;
                })
                .collect(Collectors.toList());
    }

    private ItemPropertyDefinition getItemPropertyDefinition(ItemPropertyDTO itemPropertyDTO, ItemType type) {
        ItemPropertyDefinition itemPropertyDefinition = new ItemPropertyDefinition();

        itemPropertyDefinition.setId(itemPropertyDTO.getPropertyId());
        itemPropertyDefinition.setItemType(type);
        itemPropertyDefinition.setPropertyName(itemPropertyDTO.getProperty());
        return itemPropertyDefinition;
    }

    @Override
    public List<ItemDTO> createListFromEntities(List<Item> entities) {
        return entities
                .stream()
                .map(this::createFromEntity)
                .collect(Collectors.toList());
    }
}
