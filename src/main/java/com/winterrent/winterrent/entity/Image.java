package com.winterrent.winterrent.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "item_images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "item_id")
    private int itemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id == image.id &&
                Objects.equals(name, image.name) &&
                Objects.equals(contentType, image.contentType) &&
                Arrays.equals(photo, image.photo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, contentType);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
