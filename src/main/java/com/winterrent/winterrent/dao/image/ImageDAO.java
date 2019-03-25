package com.winterrent.winterrent.dao.image;

import com.winterrent.winterrent.entity.Image;

import java.util.Optional;

public interface ImageDAO {
    Image addImage(Image image);
    Optional<Image> findImage(int itemId);
}