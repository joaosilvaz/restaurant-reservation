package com.example.demo.repository;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecturer<Booking> {
    // Page<Booking>findByDescriptionConstainiIgnoringCase(String description, Pageable pageable);
}