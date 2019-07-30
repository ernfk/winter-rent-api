package services;

import com.winterrent.winterrent.dao.item.ItemDAO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.entity.ItemProperty;
import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.entity.ItemType;
import com.winterrent.winterrent.rest.exceptions.ItemNotFound;
import com.winterrent.winterrent.service.item.ItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTests {

    @Mock
    private ItemDAO itemDAO;

    @InjectMocks
    private ItemServiceImpl itemService;

    private Item item;

    @Before
    public void setUp() {
        item = new Item();

        ItemPropertyDefinition itemPropertyDefinition = new ItemPropertyDefinition();
        itemPropertyDefinition.setItemType(ItemType.SKI);
        itemPropertyDefinition.setId(0);
        itemPropertyDefinition.setPropertyName("model");

        ItemProperty itemProperty = new ItemProperty();
        itemProperty.setId(1);
        itemProperty.setItemType(ItemType.SKI);
        itemProperty.setValue("NewSuperModel");
        itemProperty.setItemPropertyDefinition(itemPropertyDefinition);
        itemProperty.setItem(item);

        ItemProperty itemPropertyTwo = new ItemProperty();
        itemPropertyTwo.setId(2);
        itemPropertyTwo.setItemType(ItemType.SKI);
        itemPropertyTwo.setValue("500");
        itemPropertyTwo.setItemPropertyDefinition(itemPropertyDefinition);
        itemPropertyTwo.setItem(item);

        List<ItemProperty> itemProperties = new ArrayList<>();
        itemProperties.add(itemProperty);
        itemProperties.add(itemPropertyTwo);

        item.setId(3);
        item.setItemType(ItemType.SKI);
        item.setItemProperties(itemProperties);
    }

    @Test
    public void addItem_shouldAddItem_successfully() {
        itemService.addItem(item);

        assertTrue(item.getModelNo().startsWith("N"));
        Mockito.verify(itemDAO, Mockito.times(1)).saveOrUpdate(Mockito.any());
    }

    @Test
    public void findItem_shouldFindItem_successfully() {
        Mockito.when(itemDAO.findItem(3)).thenReturn(Optional.of(item));

        Item item = itemService.findItem(3);

        Mockito.verify(itemDAO, Mockito.times(1)).findItem(3);
        assertEquals(3, item.getId());
    }

    @Test(expected = ItemNotFound.class)
    public void findItem_shouldThrow_ItemNotFound() {
        Mockito.when(itemDAO.findItem(3)).thenReturn(Optional.ofNullable(null));

        itemService.findItem(3);

        Mockito.verify(itemDAO, Mockito.times(1)).findItem(3);
    }

    @Test
    public void deleteItem_shouldDeleteItem_successfully() {
        Mockito.when(itemDAO.findItem(3)).thenReturn(Optional.of(item));

        itemService.deleteItem(3);

        Mockito.verify(itemDAO, Mockito.times(1)).deleteItem(3);
    }

    @Test(expected = ItemNotFound.class)
    public void deleteItem_shouldThrow_ItemNotFound() {
        Mockito.when(itemDAO.findItem(3)).thenReturn(Optional.ofNullable(null));

        itemService.deleteItem(3);

        Mockito.verify(itemDAO, Mockito.times(1)).deleteItem(3);
    }

}
