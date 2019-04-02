package com.winterrent.winterrent.service.image;

import com.winterrent.winterrent.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image addImage(MultipartFile file, int itemId);
    Image findImageByItemId(int itemId);
    void deleteImage(int imageId);
}
