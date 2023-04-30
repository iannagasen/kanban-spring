package com.ia.canboom.domain;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;

  public Item addItem(ItemFormRequest form) {
    var item = toItem(form);
    return itemRepository.save(item);
  }

  public Item updateItem(Item updatedItem) {
    return itemRepository.save(updatedItem);
  }

  public Item getItemById(Long id) {
    return itemRepository.findById(id).orElseThrow();
  }

  private Item toItem(ItemFormRequest form) {
    return new Item(
        null,
        form.getTitle(),
        form.getDescription(),
        form.getAssignee(),
        form.getPriority(),
        form.getStatus(),
        form.getDateDue(),
        form.getTags(),
        form.getTimeEstimate());
  }

  public void deleteItem(Long id) {
    itemRepository.deleteById(id);
  }

}
