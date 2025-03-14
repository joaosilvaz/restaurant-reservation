package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Booking;

@RestController
public class CategoryController {
    
    
    private List<Booking> bookings = new ArrayList<>();

     // Get all bookings
    @GetMapping("/bookings")
    public List<Booking> getBookings() {
        return bookings;
    }

    // Create Bookings
    // POST
    @PostMapping("/bookings")
    public ResponseEntity<Booking> create(@RequestBody Booking booking){ 
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

        if(post.isEmpty()){
            System.out.println("Post not found");
            return ResponseEntity.notFound().build(); 
        }

        return ResponseEntity.ok(post.get());
    }
}
