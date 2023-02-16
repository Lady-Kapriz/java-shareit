package ru.practicum.shareit.booking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.state.BookingState;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("select booking from Booking booking " +
            "where booking.booker.id = :ownerId " +
            "and :currentData > booking.end")
    List<Booking> findUserBookingItem(Long ownerId, LocalDateTime currentData);

    @Query("select booking from Booking booking " +
            "where booking.booker.id = :bookerId")
    Page<Booking> findUserBookingById(Long bookerId, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.booker.id = :bookerId " +
            "and :currentData between booking.start and booking.end")
    Page<Booking> findUserBookingByCurrent(Long bookerId, LocalDateTime currentData, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.booker.id = :bookerId " +
            "and booking.end < :currentData")
    Page<Booking> findUserBookingByPast(Long bookerId, LocalDateTime currentData, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.booker.id = :bookerId " +
            "and booking.start > :currentData")
    Page<Booking> findUserBookingByFuture(Long bookerId, LocalDateTime currentData, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.booker.id = :bookerId " +
            "and booking.status = :bookingStatus")
    Page<Booking> findUserBookingByStatus(Long bookerId, BookingState bookingStatus, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.item.owner = :ownerId")
    Page<Booking> findItemBookingById(Long ownerId, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.item.owner = :ownerId " +
            "and :currentData between booking.start and booking.end")
    Page<Booking> findItemBookingByCurrent(Long ownerId, LocalDateTime currentData, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.item.owner = :ownerId " +
            "and booking.end < :currentData")
    Page<Booking> findItemBookingByPast(Long ownerId, LocalDateTime currentData, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.item.owner = :ownerId " +
            "and booking.start > :currentData")
    Page<Booking> findItemBookingByFuture(Long ownerId, LocalDateTime currentData, Pageable pageable);

    @Query("select booking from Booking booking " +
            "where booking.item.owner = :ownerId " +
            "and booking.status = :status")
    Page<Booking> findItemBookingByStatus(Long ownerId, BookingState status, Pageable pageable);

    Collection<Booking> findByItemInAndStatus(List<Item> items, BookingState status, Sort start);

    List<Booking> findByItemIdAndStatus(Long itemId, BookingState approved, Sort start);
}
