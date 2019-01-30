package com.winterrent.winterrent.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_property_definition")
public class ItemPropertyDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    public ItemPropertyDefinition() {
    }

    public ItemPropertyDefinition(String propertyName, ItemType itemType) {
        this.propertyName = propertyName;
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "ItemPropertyDefinition{" +
                "id=" + id +
                ", propertyName='" + propertyName + '\'' +
                ", itemType=" + itemType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPropertyDefinition that = (ItemPropertyDefinition) o;
        return id == that.id &&
                Objects.equals(propertyName, that.propertyName) &&
                Objects.equals(itemType, that.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyName, itemType);
    }
}
