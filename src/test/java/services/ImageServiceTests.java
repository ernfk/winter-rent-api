package services;

import com.winterrent.winterrent.dao.image.ImageDAO;
import com.winterrent.winterrent.entity.Image;
import com.winterrent.winterrent.rest.exceptions.ImageNotFound;
import com.winterrent.winterrent.service.image.ImageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTests {

    @Mock
    private ImageDAO imageDAO;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Mock
    private MultipartFile file;

    private int itemId;
    private int imageId;
    private Image image;

    @Before
    public void setUp() {
        itemId = 1;
        imageId = 2;
        image = new Image();
        image.setItemId(itemId);
    }

    @Test
    public void addItem_shouldAddImage_successfully() {
        Mockito.when(imageDAO.addImage(image)).thenReturn(image);

        Image result = imageService.addImage(file, itemId);

        Mockito.verify(imageDAO, Mockito.times(1)).addImage(Mockito.any());
        assertEquals(result.getItemId(), 1);
    }

    @Test
    public void findImageById_shouldReturnImage_successfully() {
        Mockito.when(imageDAO.findImageByItemId(itemId)).thenReturn(Optional.of(image));

        Image result = imageService.findImageByItemId(itemId);

        Mockito.verify(imageDAO, Mockito.times(1)).findImageByItemId(itemId);
        assertEquals(result.getItemId(), 1);
    }

    @Test(expected = ImageNotFound.class)
    public void findImageByItemId_shouldThrow_ImageNotFound() {
        Mockito.when(imageDAO.findImageByItemId(itemId)).thenReturn(Optional.ofNullable(null));

        imageService.findImageByItemId(itemId);
    }

    @Test
    public void deleteImage_shouldDeleteImage_successfully() {
        Mockito.when(imageDAO.findImageById(imageId)).thenReturn(Optional.of(image));

        imageService.deleteImage(imageId);

        Mockito.verify(imageDAO, Mockito.times(1)).deleteImage(image);
    }

    @Test(expected = ImageNotFound.class)
    public void deleteImage_shouldThrow_ImageNotFound() {
        Mockito.when(imageDAO.findImageById(imageId)).thenReturn(Optional.ofNullable(null));

        imageService.deleteImage(imageId);
    }

    @Test
    public void updateImage_shouldUpdateImage_successfully() {
        image.setId(imageId);
        Mockito.when(imageDAO.updateItem(image)).thenReturn(image);

        Image result = imageService.updateImage(file, imageId, itemId);

        Mockito.verify(imageDAO, Mockito.times(1)).updateItem(Mockito.any());
        assertEquals(result.getItemId(), 1);
        assertEquals(result.getId(), imageId);
    }
}
