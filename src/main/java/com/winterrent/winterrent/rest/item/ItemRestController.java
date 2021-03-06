package com.winterrent.winterrent.rest.item;

import com.winterrent.winterrent.converters.ItemToItemDTOConverter;
import com.winterrent.winterrent.dto.ItemDTO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemRestController {

    private static final ItemToItemDTOConverter ITEM_TO_ITEM_DTO_CONVERTER = new ItemToItemDTOConverter();
    private ItemService itemService;

    @Autowired
    public ItemRestController(ItemService theItemService) {
        this.itemService = theItemService;
    }

    @GetMapping("/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<ItemDTO> findAll() {
        List<Item> items = this.itemService.findAll();
        return ITEM_TO_ITEM_DTO_CONVERTER.createListFromEntities(items);
    }

    @PostMapping("/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ItemDTO addItem(@RequestBody ItemDTO itemDTO) {
        itemDTO.setId(0);
        Item item = ITEM_TO_ITEM_DTO_CONVERTER.createFromDTO(itemDTO);
        Item result = this.itemService.addItem(item);
        return ITEM_TO_ITEM_DTO_CONVERTER.createFromEntity(result);
    }

    @GetMapping("items/{itemId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ItemDTO findItem(@PathVariable int itemId) {
        Item item = this.itemService.findItem(itemId);
        return ITEM_TO_ITEM_DTO_CONVERTER.createFromEntity(item);
    }

    @PutMapping("/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ItemDTO updateItem(@RequestBody ItemDTO itemDTO) {
        Item item = ITEM_TO_ITEM_DTO_CONVERTER.createFromDTO(itemDTO);
        Item result = this.itemService.updateItem(item);
        return ITEM_TO_ITEM_DTO_CONVERTER.createFromEntity(result);
    }

    @DeleteMapping("items/{itemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteItem(@PathVariable int itemId) {
        this.itemService.deleteItem(itemId);
    }

}
