package com.winterrent.winterrent.service.image;

import com.winterrent.winterrent.dao.image.ImageDAO;
import com.winterrent.winterrent.entity.Image;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.service.image.exceptions.ImageNotFound;
import com.winterrent.winterrent.service.item.exceptions.ItemNotFound;
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
        Image image = new Image();

        String name = file.getOriginalFilename();
        String type = file.getContentType();

        image.setId(0);
        image.setName(name);
        image.setContentType(type);

        try {
            byte[] bytes = file.getBytes();
            image.setPhoto(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        image.setItemId(itemId);

        LOGGER.info("Adding new image with name: {}, type: {}.", name, type);
        return this.imageDAO.addImage(image);
    }

    @Override
    public Image findImage(int itemId) {
        LOGGER.info("Getting image by item id: {}", itemId);
        Optional<Image> opt = this.imageDAO.findImage(itemId);
        opt.orElseThrow(() -> new ImageNotFound("Can not find image for item with id: " + itemId));
        return opt.get();
    }
}
