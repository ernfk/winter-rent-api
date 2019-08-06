package com.winterrent.winterrent.rest.item;

import com.winterrent.winterrent.converters.ItemToItemDTOConverter;
import com.winterrent.winterrent.dto.ItemDTO;
import com.winterrent.winterrent.entity.Item;
import com.winterrent.winterrent.rest.responses.Response;
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
    Response findAll() {
        List<Item> items = this.itemService.findAll();
        List<ItemDTO> dtos = ITEM_TO_ITEM_DTO_CONVERTER.createListFromEntities(items);
        return new Response(200, "Successfully found items", dtos);
    }

    @PostMapping("/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Response addItem(@RequestBody ItemDTO itemDTO) {
        itemDTO.setId(0);
        Item item = ITEM_TO_ITEM_DTO_CONVERTER.createFromDTO(itemDTO);
        Item result = this.itemService.addItem(item);
        ItemDTO dto = ITEM_TO_ITEM_DTO_CONVERTER.createFromEntity(result);
        return new Response(201, "Added new item", dto);
    }

    @GetMapping("items/{itemId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Response findItem(@PathVariable int itemId) {
        Item item = this.itemService.findItem(itemId);
        ItemDTO dto = ITEM_TO_ITEM_DTO_CONVERTER.createFromEntity(item);
        return new Response(200, "Successfully found item", dto);
    }

    @PutMapping("/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Response updateItem(@RequestBody ItemDTO itemDTO) {
        Item item = ITEM_TO_ITEM_DTO_CONVERTER.createFromDTO(itemDTO);
        Item result = this.itemService.updateItem(item);
        ItemDTO dto = ITEM_TO_ITEM_DTO_CONVERTER.createFromEntity(result);
        return new Response(200, "Updated item successfully", dto);
    }

    @DeleteMapping("items/{itemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteItem(@PathVariable int itemId) {
        this.itemService.deleteItem(itemId);
    }

}
