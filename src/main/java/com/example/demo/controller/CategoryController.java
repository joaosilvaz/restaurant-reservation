package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Booking;

@RestController
public class CategoryController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Booking> bookings = new ArrayList<>();

    // Get all bookings
    @GetMapping("/bookings")
    public List<Booking> getBookings() {
        return bookings;
    }

    // Create Bookings
    // POST
    @PostMapping("/bookings")
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        System.out.println("Cadastrando..." + booking.getUser());
        bookings.add(booking);
        return ResponseEntity.status(201).body(booking);
    }

    // Get details of a booking
    // GET
    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        var post = bookings
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (post.isEmpty()) {
            System.out.println("Post not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(post.get());
    }

    @DeleteMapping("/bookings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando reserva " + id);

        // Obtém o Booking de dentro do ResponseEntity
        Booking booking = getBooking(id).getBody();

        if (booking != null) {
            bookings.remove(booking);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada");
        }
    }

    @PutMapping("/bookings/{id}")
    public Booking update(@PathVariable Long id, @RequestBody Booking booking) {
        log.info("Atualizando categoria " + id + " " + booking);

        // Obtém o Booking de dentro do ResponseEntity
        Booking existingBooking = getBooking(id).getBody();

        if (existingBooking != null) {
            bookings.remove(existingBooking);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada");
        }

        booking.setId(id);
        bookings.add(booking);

        return booking;
    }

}
