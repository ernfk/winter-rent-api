package com.winterrent.winterrent.rest.image;

import com.winterrent.winterrent.entity.Image;
import com.winterrent.winterrent.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Image addImage(@RequestParam("itemId") int id, @RequestParam("file") MultipartFile file) {
        return this.imageService.addImage(file, id);
    }

    @GetMapping(path = "/images/{itemId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Image findImageByItemId(@PathVariable int itemId) {
        return this.imageService.findImageByItemId(itemId);
    }

    @DeleteMapping(path = "/images/{imageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteImage(@PathVariable int imageId) {
        this.imageService.deleteImage(imageId);
    }

    @PutMapping(path = "images/{imageId}/items/{itemId}", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Image updateImage(@PathVariable("imageId") int imageId, @PathVariable("itemId") int itemId,@RequestParam("file") MultipartFile file) {
        return this.imageService.updateImage(file, imageId, itemId);
    }
}
