package com.winterrent.winterrent.rest.image;

import com.winterrent.winterrent.entity.Image;
import com.winterrent.winterrent.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImageRestController {

    private ImageService imageService;

    @Autowired
    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(path = "/images", consumes = "multipart/form-data")
    Image addImage(@RequestParam("itemId") int id, @RequestParam("file") MultipartFile file) {
        return this.imageService.addImage(file, id);
    }

    @GetMapping(path = "/images/{itemId}")
    Image findImage(@PathVariable int itemId) {
        return this.imageService.findImage(itemId);
    }
}
