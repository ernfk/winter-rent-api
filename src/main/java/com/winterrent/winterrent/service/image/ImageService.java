package com.winterrent.winterrent.service.image;

import com.winterrent.winterrent.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image addImage(MultipartFile file, int itemId);
    Image findImageByItemId(int itemId);
    Image findImageById(int imageId);
    void deleteImage(int imageId);
    Image updateImage(MultipartFile file, int imageId, int itemId);
}
