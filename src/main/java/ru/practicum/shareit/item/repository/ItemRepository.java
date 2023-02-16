package ru.practicum.shareit.item.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(" select item from Item item " +
            "where upper(item.name) like upper(concat('%', :text, '%')) " +
            "or upper(item.description) like upper(concat('%', :text, '%')) " +
            "and item.available = true")
    List<Item> search(String text, Pageable pageable);

    List<Item> findByOwner(Long ownerId, Pageable pageable);

    List<Item> findByRequest(ItemRequest itemRequest, Sort id);

    List<Item> findByRequestIn(List<ItemRequest> itemRequests, Sort id);
}
