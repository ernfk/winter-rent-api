package com.winterrent.winterrent.dto;

import com.winterrent.winterrent.entity.ItemType;

import java.util.List;

public class ItemDTO {
    private int id;
    private ItemType itemType;
    private String modelNo;
    private List<ItemPropertyDTO> itemProperties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public List<ItemPropertyDTO> getItemProperties() {
        return itemProperties;
    }

    public void setItemProperties(List<ItemPropertyDTO> itemProperties) {
        this.itemProperties = itemProperties;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", itemType=" + itemType +
                ", modelNo='" + modelNo + '\'' +
                ", itemProperties=" + itemProperties +
                '}';
    }
}
