package com.winterrent.winterrent.converters;

import com.winterrent.winterrent.dto.ItemDTO;
import com.winterrent.winterrent.dto.ItemPropertyDTO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.entity.ItemProperty;

import java.util.List;
import java.util.stream.Collectors;

public class ItemToItemDTOConverter implements GenericConverter<Item, ItemDTO> {

    @Override
    public ItemDTO createFromEntity(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setModelNo(item.getModelNo());
        itemDTO.setItemType(item.getItemType());
        itemDTO.setItemProperties(getItemProperties(item.getItemProperties()));
        return itemDTO;
    }

    private List<ItemPropertyDTO> getItemProperties(List<ItemProperty> itemProperties) {
        return itemProperties
                .stream()
                .map(itemProperty -> {
                    ItemPropertyDTO itemPropertyDTO = new ItemPropertyDTO();
                    itemPropertyDTO.setId(itemProperty.getId());
                    itemPropertyDTO.setProperty(itemProperty.getItemPropertyDefinition().getPropertyName());
                    itemPropertyDTO.setValue(itemProperty.getValue());
                    return itemPropertyDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Item createFromDTO(ItemDTO dto) {
        throw new IllegalArgumentException("Not implemented yet");
    }
}
