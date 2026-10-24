package com.campusmarket.service;

import com.campusmarket.dto.ItemCreateRequest;
import com.campusmarket.model.Item;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {
    private final Map<Long, Item> items = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Item getById(Long id) {
        return items.get(id);
    }

    public Item create(ItemCreateRequest request) {
        long id = idGenerator.incrementAndGet();
        Item item = new Item(
                id,
                request.title(),
                request.price(),
                request.categoryId().longValue(),
                request.description(),
                request.stock(),
                request.status()
        );
        items.put(id, item);
        return item;
    }

    public Item update(Long id, ItemCreateRequest request) {
        if (!items.containsKey(id)) {
            return null;
        }
        Item item = new Item(
                id,
                request.title(),
                request.price(),
                request.categoryId().longValue(),
                request.description(),
                request.stock(),
                request.status()
        );
        items.put(id, item);
        return item;
    }

    public List<Item> list() {
        return items.values()
                .stream()
                .sorted(Comparator.comparing(Item::id))
                .toList();
    }

    public boolean deleteById(Long id) {
        Item removedItem = items.remove(id);
        return removedItem != null;
    }

    public List<Item> getByStatus(String status) {
        if (status == null || status.isBlank()) {
            return list();
        }
        return items.values().stream()
                .filter(item -> status.equals(item.status()))
                .sorted(Comparator.comparing(Item::id))
                .toList();
    }
}