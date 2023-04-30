package com.ia.canboom.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ia.canboom.domain.ItemFormRequest;
import com.ia.canboom.domain.Item;
import com.ia.canboom.domain.ItemRepository;
import com.ia.canboom.domain.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/column")
@RequiredArgsConstructor
@Slf4j
public class ItemResource {

  private final ItemRepository columnRepository;
  private final ItemService itemService;

  @CrossOrigin
  @GetMapping
  public List<Item> getColumns() {
    log.info("Starting ColumnResource::getColumns");
    return columnRepository.findAll();
  }

  @CrossOrigin
  @PostMapping
  public Item addItem(@RequestBody ItemFormRequest addItemForm) {
    log.info("adding item");
    return itemService.addItem(addItemForm);
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  public Long deleteItem(@PathVariable Long id) {
    log.info("deleting id " + id);
    itemService.deleteItem(id);
    return id;
  }

  @CrossOrigin
  @PutMapping("/{id}")
  public Item updateItem(

      @PathVariable("id") Long id,
      @RequestParam Optional<String> title,
      @RequestParam Optional<String> description,
      @RequestParam Optional<String> asignee,
      @RequestParam Optional<String> priority,
      @RequestParam Optional<String> status,
      @RequestParam Optional<LocalDate> dateDue,
      @RequestParam Optional<String> tags,
      @RequestParam Optional<Integer> timeEstimate

  ) {

    var existingItem = itemService.getItemById(id);

    title.ifPresent(existingItem::setTitle);
    description.ifPresent(existingItem::setDescription);
    asignee.ifPresent(existingItem::setAssignee);
    priority.ifPresent(existingItem::setPriority);
    status.ifPresent(existingItem::setStatus);
    dateDue.ifPresent(existingItem::setDateDue);
    tags.ifPresent(existingItem::setTags);
    timeEstimate.ifPresent(existingItem::setTimeEstimate);

    var updatedItem = itemService.updateItem(existingItem);
    return updatedItem;
  }
}
