package services;

import com.winterrent.winterrent.dao.image.ImageDAO;
import com.winterrent.winterrent.entity.Image;
import com.winterrent.winterrent.service.image.ImageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTests {

    @Mock
    private ImageDAO imageDAO;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Mock
    private
    MultipartFile file;

    @Test
    public void addItem_shouldAddImage_successfully() {
        int itemId = 1;

        Image image = imageService.addImage(file, itemId);

        Mockito.verify(imageDAO, Mockito.times(1)).addImage(Mockito.any());
        System.out.println(image);
        assertEquals(image.getItemId(), 122);
    }

}
