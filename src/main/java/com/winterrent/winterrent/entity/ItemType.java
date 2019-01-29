package com.winterrent.winterrent.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_type")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    public ItemType() {

    }

    public ItemType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ItemType{id=" + id + ", type='" + type + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemType itemType = (ItemType) o;
        return id == itemType.id &&
                Objects.equals(type, itemType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
