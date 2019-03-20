package com.winterrent.winterrent.rest.image;

import com.winterrent.winterrent.entity.Image;
import com.winterrent.winterrent.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class ImageRestController {

    private ImageService imageService;

    @Autowired
    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images")
    Image addImage(@RequestParam("file") MultipartFile file) {
        Image image = new Image();
        image.setId(0);
        image.setName(file.getName());
        image.setExtension(file.getContentType());
        try {
            byte[] bytes = file.getBytes();
            image.setPhoto(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setItemId(62);
        return this.imageService.addImage(image);
    }
}
