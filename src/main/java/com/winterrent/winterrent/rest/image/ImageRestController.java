package com.winterrent.winterrent.rest.image;

import com.winterrent.winterrent.entity.Image;
import com.winterrent.winterrent.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        return this.imageService.addImage(file);
    }
}
