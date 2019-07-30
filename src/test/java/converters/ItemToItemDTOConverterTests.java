package converters;

import com.winterrent.winterrent.converters.ItemToItemDTOConverter;
import com.winterrent.winterrent.dto.ItemDTO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.entity.ItemProperty;
import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.entity.ItemType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ItemToItemDTOConverterTests {

    private static ItemToItemDTOConverter ITEM_TO_ITEM_DTO_CONVERTER = new ItemToItemDTOConverter();

    @Test
    public void shouldConvertItemToItemDTO() {
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

        List<ItemProperty> itemPropertyList = new ArrayList<>();
        itemPropertyList.add(itemProperty);
        itemPropertyList.add(itemPropertyTwo);

        Item item = new Item();
        item.setId(2);
        item.setModelNo("Model");
        item.setItemType(ItemType.SKI);
        item.setItemProperties(itemPropertyList);

        ItemDTO result = ITEM_TO_ITEM_DTO_CONVERTER.createFromEntity(item);

        assertEquals(2, result.getId());
        assertEquals(2, result.getItemProperties().size());
        assertEquals("250", result.getItemProperties().get(0).getValue());
        assertEquals(ItemType.SKI, result.getItemType());
        assertEquals("Model", result.getModelNo());
    }

}
