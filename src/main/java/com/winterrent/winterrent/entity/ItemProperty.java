package com.winterrent.winterrent.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_properties")
public class ItemProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_property_definition_id")
    private ItemPropertyDefinition itemPropertyDefinition;

    @Column(name = "value")
    private String value;

    public ItemProperty(){}

    public ItemProperty(Item item, ItemPropertyDefinition itemPropertyDefinition, String value) {
        this.item = item;
        this.itemPropertyDefinition = itemPropertyDefinition;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemPropertyDefinition getItemPropertyDefinition() {
        return itemPropertyDefinition;
    }

    public void setItemPropertyDefinition(ItemPropertyDefinition itemPropertyDefinition) {
        this.itemPropertyDefinition = itemPropertyDefinition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ItemProperty{" +
                "id=" + id +
                ", item=" + item +
                ", itemPropertyDefinition=" + itemPropertyDefinition +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemProperty that = (ItemProperty) o;
        return id == that.id &&
                Objects.equals(item, that.item) &&
                Objects.equals(itemPropertyDefinition, that.itemPropertyDefinition) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, itemPropertyDefinition, value);
    }
}
