package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.RequestNotFoundException;
import ru.practicum.shareit.item.dto.ItemDtoForRequest;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDtoForGet;
import ru.practicum.shareit.request.dto.ItemRequestDtoOut;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.ItemRequestRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ItemRequestServiceImpl implements ItemRequestService {
    private final ItemRepository itemRepository;
    private final ItemRequestRepository itemRequestRepository;
    private final UserService userService;
    private final ItemRequestMapper itemRequestMapper;

    @Override
    public Collection<ItemRequestDtoForGet> getAllByOwner(Long ownerId) {
        log.info("Проверяем пользователя");
        userService.getByIdForService(ownerId);
        log.info("проверили пользователя");
        List<ItemRequest> requests = itemRequestRepository
                .findByRequestorId(ownerId, Sort.by(DESC, "created"));
        Map<ItemRequest, List<Item>> itemsMap = itemRepository
                .findByRequestIn(requests, Sort.by(ASC, "id")).stream()
                .collect(groupingBy(Item::getRequest, toList()));
        return doOutForGetList(requests, itemsMap);
    }

    @Override
    public Collection<ItemRequestDtoForGet> getAllItemRequests(Long requestorId, Integer from, Integer size) {
        userService.getByIdForService(requestorId);
        List<ItemRequest> requests = itemRequestRepository.findByRequestorIdNot(
                requestorId, PageRequest.of(from, size, Sort.by(DESC, "created")));
        Map<ItemRequest, List<Item>> itemsMap = itemRepository
                .findByRequestIn(requests, Sort.by(ASC, "id")).stream()
                .collect(groupingBy(Item::getRequest, toList()));
        return doOutForGetList(requests, itemsMap);
    }

    @Override
    public ItemRequestDtoForGet getById(Long requestId, Long userId) {
        userService.getByIdForService(userId);
        ItemRequest itemRequest = findById(requestId);
        List<Item> items = itemRepository.findByRequest(
                itemRequest, Sort.by(ASC, "id"));
        return itemRequestMapper.toItemRequestDtoForGet(
                itemRequest, extractionItemFromList(items, itemRequest));
    }

    @Transactional
    @Override
    public ItemRequestDtoOut createItemRequest(ItemRequestDto itemRequestDto, Long ownerId) {
        log.info("проверяем юзера");
        User requestor = userService.getByIdForService(ownerId);
        log.info("устанавливаем время");
        itemRequestDto.setCreated(LocalDateTime.now());
        itemRequestDto.setId(itemRequestRepository.save(
                itemRequestMapper.toItemRequest(itemRequestDto, requestor)).getId());
        return itemRequestMapper.toItemRequestDtoOut(itemRequestDto);
    }

    private List<ItemRequestDtoForGet> doOutForGetList(
            List<ItemRequest> requests, Map<ItemRequest, List<Item>> itemsMap) {
        List<ItemRequestDtoForGet> itemRequestDtoForGets = new ArrayList<>();
        requests.forEach(itemRequest -> itemRequestDtoForGets.add(itemRequestMapper
                .toItemRequestDtoForGet(itemRequest, extractionItemFromMap(itemsMap, itemRequest))));
        return itemRequestDtoForGets;
    }

    private List<ItemDtoForRequest> extractionItemFromMap(
            Map<ItemRequest, List<Item>> itemsMap, ItemRequest itemRequest) {
        return itemsMap.getOrDefault(itemRequest, List.of())
                .stream().map(item -> ItemMapper.toItemDtoForRequest(item, itemRequest))
                .collect(toList());
    }

    private ItemRequest findById(Long requestId) {
        return itemRequestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(
                        String.format("Запрос с id: %s не найден", requestId)));
    }

    private List<ItemDtoForRequest> extractionItemFromList(
            List<Item> items, ItemRequest itemRequest) {
        return items.stream().map(item -> ItemMapper.toItemDtoForRequest(item, itemRequest))
                .collect(toList());
    }
}
