package com.winterrent.winterrent.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ItemType itemType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    // @JsonManagedReference is the forward part of reference which gets serialized normally.
    @JsonManagedReference
    private List<ItemProperty> itemProperties;

    public Item() {

    }

    public Item(ItemType itemType, List<ItemProperty> itemProperties) {
        this.itemType = itemType;
        this.itemProperties = itemProperties;
    }

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

    public List<ItemProperty> getItemProperties() {
        return itemProperties;
    }

    public void setItemProperties(List<ItemProperty> itemProperties) {
        this.itemProperties = itemProperties;
    }

    @Override
    public String toString() {
        return "Item{id=" + id + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                itemType == item.itemType &&
                Objects.equals(itemProperties, item.itemProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemType, itemProperties);
    }
}
