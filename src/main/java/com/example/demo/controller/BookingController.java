package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository repository;

    @GetMapping
    public List<Booking> index() {
        return repository.findAll();
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return repository.save(booking);
    }

    @GetMapping("/{id}")
    public Booking show(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        return repository.findById(id)
                .map(b -> {
                    updatedBooking.setId(id);
                    return repository.save(updatedBooking);
                })
                .orElseThrow();
    }
}