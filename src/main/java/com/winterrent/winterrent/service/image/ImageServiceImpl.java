package com.winterrent.winterrent.service.image;

import com.winterrent.winterrent.dao.image.ImageDAO;
import com.winterrent.winterrent.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    private ImageDAO imageDAO;

    @Autowired
    public ImageServiceImpl(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Override
    public Image addImage(MultipartFile file, int itemId) {
        Image image = prepareImage(file, 0, itemId);
        LOGGER.info("Adding new image for item with id: {}", itemId);
        return this.imageDAO.addImage(image);
    }

    @Override
    public Image findImageByItemId(int itemId) {
        LOGGER.info("Getting image by item id: {}", itemId);
        Optional<Image> opt = this.imageDAO.findImageByItemId(itemId);
        return opt.orElse(null);
    }

    @Override
    public void deleteImage(int imageId) {
        LOGGER.info("Deleting image with image id: {}", imageId);
        this.imageDAO.deleteImage(imageId);
    }

    @Override
    public Image updateImage(MultipartFile file, int imageId, int itemId) {
        Image image = prepareImage(file, imageId, itemId);
        LOGGER.info("Updating image with id: {}, for item with id: {}", imageId, itemId);
        return this.imageDAO.updateItem(image);
    }

    private Image prepareImage(MultipartFile file, int imageId, int itemId) {
        Image image = new Image();

        String name = file.getOriginalFilename();
        String type = file.getContentType();

        image.setId(imageId);
        image.setName(name);
        image.setContentType(type);

        try {
            byte[] bytes = file.getBytes();
            image.setPhoto(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        image.setItemId(itemId);

        return image;
    }
}
