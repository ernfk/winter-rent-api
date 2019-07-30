package converters;

import com.winterrent.winterrent.converters.ItemToItemDTOConverter;
import com.winterrent.winterrent.dto.ItemDTO;
import com.winterrent.winterrent.dto.ItemPropertyDTO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.entity.ItemProperty;
import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.entity.ItemType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ItemToItemDTOConverterTests {

    private static ItemToItemDTOConverter ITEM_TO_ITEM_DTO_CONVERTER = new ItemToItemDTOConverter();

    private Item item;
    private Item itemTwo;

    @Before
    public void setUp() {
        ItemPropertyDefinition itemPropertyDefinition = new ItemPropertyDefinition();
        itemPropertyDefinition.setItemType(ItemType.SKI);
        itemPropertyDefinition.setId(0);
        itemPropertyDefinition.setPropertyName("Price");

        ItemProperty itemProperty = new ItemProperty();
        itemProperty.setId(1);
        itemProperty.setItemType(ItemType.SKI);
        itemProperty.setValue("250");
        itemProperty.setItemPropertyDefinition(itemPropertyDefinition);

        ItemProperty itemPropertyTwo = new ItemProperty();
        itemPropertyTwo.setId(2);
        itemPropertyTwo.setItemType(ItemType.SKI);
        itemPropertyTwo.setValue("500");
        itemPropertyTwo.setItemPropertyDefinition(itemPropertyDefinition);

        List<ItemProperty> itemProperties = new ArrayList<>();
        itemProperties.add(itemProperty);
        itemProperties.add(itemPropertyTwo);

        item = new Item();
        item.setId(2);
        item.setModelNo("Model");
        item.setItemType(ItemType.SKI);
        item.setItemProperties(itemProperties);

        itemTwo = new Item();
        itemTwo.setId(22);
        itemTwo.setModelNo("Model Two");
        itemTwo.setItemType(ItemType.BOARD);
        itemTwo.setItemProperties(itemProperties);
    }

    @Test
    public void shouldConvertItemToItemDTO() {
        ItemDTO result = ITEM_TO_ITEM_DTO_CONVERTER.createFromEntity(item);

        assertEquals(2, result.getId());
        assertEquals(2, result.getItemProperties().size());
        assertEquals("250", result.getItemProperties().get(0).getValue());
        assertEquals(ItemType.SKI, result.getItemType());
        assertEquals("Model", result.getModelNo());
    }

    @Test
    public void shouldConvertItemDTOToItem() {
        ItemPropertyDTO itemPropertyDTO = new ItemPropertyDTO();
        itemPropertyDTO.setValue("200");
        itemPropertyDTO.setProperty("Length");
        itemPropertyDTO.setId(10);

        ItemPropertyDTO itemPropertyDTOTwo = new ItemPropertyDTO();
        itemPropertyDTOTwo.setValue("Gender");
        itemPropertyDTOTwo.setProperty("Male");
        itemPropertyDTOTwo.setId(15);

        List<ItemPropertyDTO> itemPropertyDtos = new ArrayList<>();
        itemPropertyDtos.add(itemPropertyDTO);
        itemPropertyDtos.add(itemPropertyDTOTwo);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(100);
        itemDTO.setModelNo("Model DTO");
        itemDTO.setItemProperties(itemPropertyDtos);
        itemDTO.setItemType(ItemType.SKI);

        Item result = ITEM_TO_ITEM_DTO_CONVERTER.createFromDTO(itemDTO);

        assertEquals(100, result.getId());
        assertEquals(2, result.getItemProperties().size());
        assertEquals("200", result.getItemProperties().get(0).getValue());
        assertEquals(ItemType.SKI, result.getItemType());
        assertEquals("Model DTO", result.getModelNo());
        assertEquals("Length", result.getItemProperties().get(0).getItemPropertyDefinition().getPropertyName());
    }

    @Test
    public void shouldConvertListOfItemsToListOfItemDTOs() {
        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(itemTwo);

        List<ItemDTO> result = ITEM_TO_ITEM_DTO_CONVERTER.createListFromEntities(items);

        assertEquals(2, result.size());
    }
}
